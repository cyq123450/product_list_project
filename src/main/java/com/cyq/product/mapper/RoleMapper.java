package com.cyq.product.mapper;

import com.cyq.product.domain.UserRole;
import com.cyq.product.model.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 角色持久层接口
 */
public interface RoleMapper {

    /**
     * 获取所有的角色信息
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
    void updateRole(RoleVo roleVo);

    /**
     * 根据ID删除角色信息
     * @param id
     */
    void deleteRole(Integer id);

    /**
     * 通过用户ID获取角色信息
     * @param userId
     * @return
     */
    List<RoleVo> getRolesByUserId(Integer userId);

    /**
     * 根据角色ID获取用户与角色相关数据
     * @param roleId
     * @return
     */
    int getUserRoleByRoleId(Integer roleId);

    /**
     * 删除用户角色
     * @param ids
     */
    void deleteRoleForUser(@Param("ids") Set ids);
}
