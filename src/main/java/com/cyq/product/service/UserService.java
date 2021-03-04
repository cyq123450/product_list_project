package com.cyq.product.service;

import com.cyq.product.domain.UserRole;
import com.cyq.product.model.UserVo;

import java.util.List;

public interface UserService {

    /**
     * 注册用户
     * @param userVo
     */
    void registry(UserVo userVo);

    /**
     * 用户登陆
     * @param userVo
     * @param returnMsg
     */
    int login(UserVo userVo, StringBuilder returnMsg);

    /**
     * 获取所有用户
     * @return
     */
    List<UserVo> getUsers();

    /**
     * 给用户分配角色
     * @param userRoles
     */
    void addRoleForUser(List<UserRole> userRoles);

}
