package com.cyq.product.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色实体类
 */
@Data
public class RoleVo implements Serializable {

    private Integer id;
    private String roleName;
    private String info;
    private int enabled;

}
