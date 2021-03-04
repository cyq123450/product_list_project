package com.cyq.product.service.impl;

import com.cyq.product.domain.ProductDo;
import com.cyq.product.mapper.ProductMapper;
import com.cyq.product.model.PageParamVo;
import com.cyq.product.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 商品服务层接口实现类
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void getProductsForPgae(PageParamVo pageParamVo) {
        Integer pageNum = pageParamVo.getPageNum();
        Integer pageSize = pageParamVo.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ProductDo> products = productMapper.getProductsForPgae();
        PageInfo<ProductDo> pageInfo = new PageInfo<>(products);
        pageParamVo.setDatas(pageInfo.getList());
        pageParamVo.setTotalPage(pageInfo.getPages());
        pageParamVo.setTotalNum(pageInfo.getTotal());
    }

    @Override
    public void getProducts(PageParamVo pageParamVo) {
        Integer pageNum = pageParamVo.getPageNum();
        Integer pageSize = pageParamVo.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ProductDo> products = productMapper.getProducts();
        PageInfo<ProductDo> pageInfo = new PageInfo<>(products);
        pageParamVo.setDatas(pageInfo.getList());
        pageParamVo.setTotalPage(pageInfo.getPages());
        pageParamVo.setTotalNum(pageInfo.getTotal());
    }

    @Override
    @Transactional
    public void saveProduct(ProductDo product) {
        int saveOperation = 0;
        String pId = product.getId();
        if (pId == null) {
            saveOperation = 1;
            pId = UUID.randomUUID().toString().replaceAll("-", "");
            product.setCreateTime(new Date());
        }
        product.setUpdateTime(new Date());
        product.setPublish(2);
        if (saveOperation == 1) {
            productMapper.deleteProduct(pId);
        }
        productMapper.saveProduct(product);
    }

    @Override
    public ProductDo getProduct(String id) {
        return productMapper.getProduct(id);
    }
}
