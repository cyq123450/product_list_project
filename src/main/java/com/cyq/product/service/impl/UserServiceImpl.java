package com.cyq.product.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.cyq.product.domain.UserDo;
import com.cyq.product.domain.UserRole;
import com.cyq.product.mapper.RoleMapper;
import com.cyq.product.mapper.UserMapper;
import com.cyq.product.model.RoleVo;
import com.cyq.product.model.UserVo;
import com.cyq.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 用户服务层实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void registry(UserVo userVo) {
        UserDo userDo = new UserDo();
        String password = userVo.getPassword();
        userDo.setUserCode(userVo.getUserCode());
        userDo.setUserName(userVo.getUserName());
        userDo.setPassword(SecureUtil.md5(password));
        userDo.setPhone(userVo.getPassword());
        userMapper.registry(userDo);
    }

    @Override
    public int login(UserVo userVo, StringBuilder returnMsg) {
        UserDo userDo = userMapper.findUserByUserCode(userVo.getUserCode());
        if (userDo == null) {
            returnMsg.append("该用户信息不存在");
            return 0;
        }
        if (userDo.getEnabled() == 2) {
            returnMsg.append("该用户信息没有生效,请联系管理员");
            return 0;
        }
        String password = userVo.getPassword();
        if (!userDo.getPassword().equals(SecureUtil.md5(password))) {
            returnMsg.append("密码错误");
            return 0;
        }
        List<RoleVo> roles = roleMapper.getRolesByUserId(userDo.getId());
        userVo.setRoleVos(roles);
        userVo.setPassword("");
        returnMsg.append("登陆成功");
        return 1;
    }

    @Override
    public List<UserVo> getUsers() {
        List<UserVo> users = userMapper.getUsers();
        for (UserVo userVo : users) {
            List<RoleVo> roles = roleMapper.getRolesByUserId(userVo.getId());
            userVo.setRoleVos(roles);
        }
        return users;
    }

    @Override
    @Transactional
    public void addRoleForUser(List<UserRole> userRoles) {
        Set<Integer> ids = new HashSet<>();
        for(UserRole userRole : userRoles) {
            ids.add(userRole.getUserId());
        }
        roleMapper.deleteRoleForUser(ids);
        userMapper.addRoleForUser(userRoles);
    }

    @Override
    public int getUserByUserCode(String userCode) {
        return userMapper.getUserByUserCode(userCode);
    }

    @Override
    public void updateUser(UserDo userDo) {
        if (userDo.getId() == 1) {
            if (userDo.getPassword() == null) {
                return;
            }
            UserDo userDoNew = userMapper.findUserByUserCode(userDo.getUserCode());
            userDoNew.setPassword(SecureUtil.md5(userDo.getPassword()));
            userDo = userDoNew;
        }
        userMapper.updateUser(userDo);
    }
}
