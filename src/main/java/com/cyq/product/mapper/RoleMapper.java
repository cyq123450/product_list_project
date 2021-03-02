package com.cyq.product.mapper;

import com.cyq.product.domain.Role;

import java.util.List;

/**
 * 角色持久层接口
 */
public interface RoleMapper {

    /**
     * 通过用户ID获取角色信息
     * @param userId
     * @return
     */
    List<Role> getRolesByUserId(Integer userId);

}
