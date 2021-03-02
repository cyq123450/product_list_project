package com.cyq.product.controller;

import com.cyq.product.model.CommonResult;
import com.cyq.product.model.PageHelper;
import com.cyq.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品控制器
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 通过分页获取商品数据信息
     * @param pageHelper
     * @return
     */
    @GetMapping("/get-all")
    public CommonResult getProducts(@RequestBody PageHelper pageHelper) {
        try {
            PageHelper pageHelperNow = productService.getProducts(pageHelper);
            return CommonResult.success(pageHelperNow);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取商品信息失败...");
            return CommonResult.failed();
        }
    }

    /**
     * 添加一条商品数据信息
     * @return
     */
    @PostMapping("/save")
    public CommonResult saveProduct() {
        try {
            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("保存商品信息失败");
            return CommonResult.failed();
        }
    }

    /**
     * 更新商品数据信息
     * @return
     */
    @PostMapping("/update")
    public CommonResult updateProduct() {
        try{

            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("更新商品信息失败...");
            return CommonResult.failed();
        }
    }

}
