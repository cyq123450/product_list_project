package com.cyq.product.mapper;

import com.cyq.product.domain.User;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface UserMapper {

    /**
     * 通过用户名称查询用户
     * @param userName
     * @return
     */
    User findUserByUserName(String userName);

}
