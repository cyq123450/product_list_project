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

/**
 * 商品服务层接口实现类
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private static volatile Long productMaxIdBase = null;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageParamVo getProductsForPgae(PageParamVo<ProductDo> pageParamVo) {
        Integer pageNum = pageParamVo.getPageNum();
        Integer pageSize = pageParamVo.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<ProductDo> products = productMapper.getProductsForPgae(pageParamVo.getDatas());
        PageInfo<ProductDo> pageInfo = new PageInfo<>(products);
        PageParamVo<Object> datas = new PageParamVo<>();
        datas.setDatas(pageInfo.getList());
        datas.setTotalPage(pageInfo.getPages());
        datas.setTotalNum(pageInfo.getTotal());
        datas.setPageNum(pageParamVo.getPageNum());
        datas.setPageSize(pageParamVo.getPageSize());
        return datas;
    }

    @Override
    public PageParamVo getProducts(PageParamVo<ProductDo> pageParamVo) {
        Integer pageNum = pageParamVo.getPageNum();
        Integer pageSize = pageParamVo.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        ProductDo productDo = pageParamVo.getDatas();
        if (productDo == null) {
            productDo = new ProductDo();
        }
        List<ProductDo> products = productMapper.getProducts(productDo);
        PageInfo<ProductDo> pageInfo = new PageInfo<>(products);
        PageParamVo<Object> retData = new PageParamVo<>();
        retData.setDatas(pageInfo.getList());
        retData.setTotalPage(pageInfo.getPages());
        retData.setTotalNum(pageInfo.getTotal());
        retData.setPageNum(pageParamVo.getPageNum());
        retData.setPageSize(pageParamVo.getPageSize());
        return retData;
    }

    @Override
    @Transactional
    public void saveProduct(ProductDo product) {
        int saveOperation = 0;
        String pId = product.getId();
        if (pId == null) {
            saveOperation = 1;
            if (productMaxIdBase == null) {
                synchronized (ProductServiceImpl.class) {
                    if (productMaxIdBase == null) {
                        String productMaxIdBaseStr = productMapper.getProductMaxId();
                        if (productMaxIdBaseStr == null || "".equals(productMaxIdBaseStr)) {
                            productMaxIdBase = 0L;
                        } else {
                            productMaxIdBase = Long.valueOf(productMaxIdBaseStr.substring(2, productMaxIdBaseStr.length()));
                        }
                    }
                }
            }
            long numId = 0;
            synchronized (ProductServiceImpl.class) {
                numId = ++productMaxIdBase;
            }
            pId = String.valueOf("PM" + String.format("%06d", numId));
            product.setId(pId);
            product.setCreateTime(new Date());
        }
        product.setUpdateTime(new Date());
        if (saveOperation == 0) {
            productMapper.deleteProduct(pId);
        }
        productMapper.saveProduct(product);
    }

    @Override
    public ProductDo getProduct(String id) {
        return productMapper.getProduct(id);
    }

    @Override
    public void delAndPublishProduct(String id, int opertionType, int operationVal) {
        productMapper.delAndPublishProduct(id, opertionType, operationVal);
    }
}
