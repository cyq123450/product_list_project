package com.cyq.product.service.impl;

import com.cyq.product.domain.User;
import com.cyq.product.mapper.UserMapper;
import com.cyq.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户服务层实现类
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.findUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        return user;
    }
}
