package com.cyq.product.service;

import com.cyq.product.model.RoleVo;

import java.util.List;

/**
 * 角色服务层接口
 */
public interface RoleService {

    /**
     * 获取所有的角色配置信息
     * @return
     */
    List<RoleVo> findRoles();

    /**
     * 添加角色信息
     * @param roleVo
     */
    void addRole(RoleVo roleVo);

    /**
     * 更新角色信息
     * @param roleVo
     */
    int updateRole(RoleVo roleVo, StringBuilder returnMsg);

    /**
     * 根据ID删除角色信息
     * @param id
     */
    void deleteRole(Integer id);

}
