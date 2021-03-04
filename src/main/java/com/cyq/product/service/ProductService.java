package com.cyq.product.service;

import com.cyq.product.domain.ProductDo;
import com.cyq.product.model.PageParamVo;

/**
 * 商品服务层接口
 */
public interface ProductService {

    /**
     * 获取商品信息
     * @param PageParamVo
     * @return
     */
    void getProductsForPgae(PageParamVo PageParamVo);

    /**
     * 获取商品信息
     * @param pageParamVo
     * @return
     */
    void getProducts(PageParamVo pageParamVo);

    /**
     * 保存一条商品数据信息
     * @param product
     */
    void saveProduct(ProductDo product);

    /**
     * 获取一条商品数据信息
     * @param id
     * @return
     */
    ProductDo getProduct(String id);

}
