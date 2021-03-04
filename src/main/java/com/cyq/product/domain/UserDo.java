package com.cyq.product.domain;

import lombok.Data;
import java.util.List;

/**
 * 用户实体类
 */
@Data
public class UserDo {
    // ID
    private Integer id;
    // 用户代码
    private String userCode;
    // 用户名称
    private String userName;
    // 密码
    private String password;
    // 电话
    private String phone;
    // 是否删除
    private String del;
    // 是否生效
    private int enabled;
    // 角色
    private List<RoleDo> roles;

}
