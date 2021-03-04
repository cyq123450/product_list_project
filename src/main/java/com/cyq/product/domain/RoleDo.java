package com.cyq.product.domain;

import lombok.Data;

/**
 * 角色实体类
 */
@Data
public class RoleDo {

    // ID
    private Integer id;
    // 角色名称
    private String roleName;
    // 是否删除
    private int del;
    // 是否启动
    private int enabled;
    // 信息
    private String info;

}
