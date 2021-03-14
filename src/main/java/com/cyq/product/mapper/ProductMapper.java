package com.cyq.product.mapper;

import com.cyq.product.domain.ProductDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品持久层接口
 */
public interface ProductMapper {

    /**
     * 获取商品列表信息
     * @return
     */
    List<ProductDo> getProductsForPgae(ProductDo productDo);

    /**
     * 获取商品列表信息
     * @return
     */
    List<ProductDo> getProducts(ProductDo productDo);

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

    /**
     * 删除或发布商品
     * @param id
     * @param opertionType
     * @param operationVal
     */
    void delAndPublishProduct(@Param("id") String id,
                              @Param("opType") int opertionType,
                              @Param("opVal") int operationVal);

    /**
     * 获取最大ID值
     * @return
     */
    String getProductMaxId();

}
