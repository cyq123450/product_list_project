package com.cyq.product.controller;

import com.cyq.product.model.CommonResult;
import com.cyq.product.model.RoleVo;
import com.cyq.product.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色控制器
 */
@RestController
@RequestMapping("/api/sys-role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/get-all")
    public CommonResult getRoles() {
        try {
            List<RoleVo> roles = roleService.findRoles();
            return CommonResult.success(roles);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("获取角色信息失败");
        }
    }

    @RequestMapping("/add")
    public CommonResult addRole(@RequestBody RoleVo roleVo) {
        try {
            roleService.addRole(roleVo);
            return getRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("添加失败");
        }
    }

    @RequestMapping("/update")
    public CommonResult updateRole(@RequestBody RoleVo roleVo) {
        try {
            StringBuilder returnMsg = new StringBuilder();
            int result = roleService.updateRole(roleVo, returnMsg);
            if (result == 0) {
                return CommonResult.failed(returnMsg.toString());
            }
            return getRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("更新失败");
        }
    }

    @GetMapping("/del")
    public CommonResult deleteRole(Integer id) {
        try {
            if (id == 1 || id == 2) {
                return CommonResult.failed("系统管理员角色不允许删除");
            }
            roleService.deleteRole(id);
            return getRoles();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("删除失败");
        }
     }

}
