package com.cyq.product.domain;

import lombok.Data;

import java.util.Date;

/**
 * 商品实体类
 */
@Data
public class ProductDo {

    // 商品ID
    private String id;
    // 商品名称
    private String name;
    // 商品原价
    private String originPrice;
    // 商品折后价
    private String discountPrice;
    // 商品种类名称
    private String classification;
    // 邮费
    private String postage;
    // 商品描述
    private String desc;
    // 商品图片URL
    private String imageMajorUrl;
    // 商品图片URL
    private String imageUrls;
    // 商品是否发布,1:已发布 2:未发布
    private int publish;
    // 商品详情
    private String detailsInfo;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;
    // 顺序
    private Integer order;
    // 商品分类ID
    private Integer category;
}
