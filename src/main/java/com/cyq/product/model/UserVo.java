package com.cyq.product.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * User实体类
 */
@Data
public class UserVo implements Serializable {

    // ID
    private Integer id;
    // 用户代码
    private String userCode;
    // 用户名
    private String userName;
    // 电话
    private String phone;
    // 密码
    private String password;
    // 角色
    private List<RoleVo> roleVos;

}
