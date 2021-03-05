package com.cyq.product.controller;

import com.cyq.product.domain.UserDo;
import com.cyq.product.domain.UserRole;
import com.cyq.product.model.CommonResult;
import com.cyq.product.model.UserVo;
import com.cyq.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册用户信息
     * @param userVo
     * @return
     */
    @PostMapping("/registry")
    public CommonResult registry(@RequestBody UserVo userVo) {
        try {
            int retVal = userService.getUserByUserCode(userVo.getUserCode());
            if (retVal > 0) {
                return CommonResult.failed("该用户账号已被使用");
            }
            if (userVo.getUserCode() == null || userVo.getUserName() ==null || userVo.getPassword() == null) {
                return CommonResult.failed("用户账号或用户名或密码不能为空");
            }
            userService.registry(userVo);
            return CommonResult.success("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("注册失败");
        }
    }

    /**
     * 用户登陆
     * @param userVo
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserVo userVo, HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder returnMsg = new StringBuilder();
            int retVal = userService.login(userVo, returnMsg);
            if (retVal == 0) {
                return CommonResult.failed(returnMsg.toString());
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("userCode", userVo.getUserCode());
                session.setAttribute("userName", userVo.getUserName());
                session.setAttribute("userRoles", userVo.getRoleVos());
            }
            return CommonResult.success("登陆成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("登陆失败");
        }
    }

    /**
     * 用户注销
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public CommonResult logout(HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("userCode");
            session.removeAttribute("userName");
            session.removeAttribute("userRoles");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("注销失败");
        }
        return CommonResult.success("注销成功", null);
    }

    @GetMapping("/sys-user/get-users")
    public CommonResult getUsers() {
        try {
            List<UserVo> users = userService.getUsers();
            return CommonResult.success(users);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("获取用户信息失败");
        }
    }

    @PostMapping("/sys-user/add-user-role")
    public CommonResult addRoleForUser(@RequestBody List<UserRole> userRoles) {
        try {
            for (UserRole userRole : userRoles) {
                if (userRole.getUserId() == 1) {
                    return CommonResult.failed("根用户不允许分配角色");
                }
            }
            userService.addRoleForUser(userRoles);
            return getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("分配角色失败");
        }
    }

    @PostMapping("/sys-user/update")
    public CommonResult updateUser(@RequestBody UserDo userDo) {
        try {
            userService.updateUser(userDo);
            return getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("更新失败");
        }
    }

}
