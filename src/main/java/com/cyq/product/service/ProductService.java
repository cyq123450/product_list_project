package com.cyq.product.service;

import com.cyq.product.model.PageHelper;

/**
 * 商品服务层接口
 */
public interface ProductService {

    /**
     * 获取商品信息
     * @param pageHelper
     * @return
     */
    PageHelper getProducts(PageHelper pageHelper);

}
