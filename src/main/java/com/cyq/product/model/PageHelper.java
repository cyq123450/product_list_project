package com.cyq.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页使用实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageHelper<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalNum;
    private Integer totalPage;
    private T datas;

}
