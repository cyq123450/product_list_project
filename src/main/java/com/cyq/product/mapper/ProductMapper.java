package com.cyq.product.mapper;

import com.cyq.product.domain.ProductDo;

import java.util.List;

/**
 * 商品持久层接口
 */
public interface ProductMapper {

    /**
     * 获取商品列表信息
     * @return
     */
    List<ProductDo> getProductsForPgae();

    /**
     * 获取商品列表信息
     * @return
     */
    List<ProductDo> getProducts();

    /**
     * 保存一条商品数据信息
     * @param product
     */
    void saveProduct(ProductDo product);

    /**
     * 删除一条商品数据信息
     * @param id
     */
    void deleteProduct(String id);

    /**
    获取一条商品数据信息
     */
    ProductDo getProduct(String id);
}
