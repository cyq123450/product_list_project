package com.cyq.product.service.impl;

import com.cyq.product.mapper.ProductMapper;
import com.cyq.product.model.PageHelper;
import com.cyq.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品服务层接口实现类
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageHelper getProducts(PageHelper pageHelper) {
        return null;
    }
}
