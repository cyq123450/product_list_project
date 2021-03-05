package com.cyq.product.mapper;


import com.cyq.product.domain.UserDo;
import com.cyq.product.domain.UserRole;
import com.cyq.product.model.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 用户持久层接口
 */
public interface UserMapper {

    /**
     * 注册用户
     * @param userDo
     * @return
     */
    void registry(UserDo userDo);

    /**
     * 通过userCode查询用户信息
     * @param userCode
     */
    UserDo findUserByUserCode(String userCode);

    /**
     * 获取所有用户
     * @return
     */
    List<UserVo> getUsers();

    /**
     * 根据用户ID删除角色信息
     * @param ids
     */
    void deleteRoleForUser(@Param("ids") Set<Integer> ids);

    /**
     * 给用户分配角色
     * @param userRoles
     */
    void addRoleForUser(@Param("userRoles") List<UserRole> userRoles);

    /**
     * 根据用户代码查看是否有对应的用户
     * @param userCode
     * @return
     */
    int getUserByUserCode(String userCode);

    /**
     * 用户更新
     * @param userDo
     */
    void updateUser(UserDo userDo);

}
