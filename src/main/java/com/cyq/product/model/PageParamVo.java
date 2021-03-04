package com.cyq.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页使用实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParamVo<T> implements Serializable {

    private Integer pageNum;
    private Integer pageSize;
    private Long totalNum;
    private Integer totalPage;
    private T datas;

}
