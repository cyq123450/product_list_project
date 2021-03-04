package com.cyq.product.controller;

import com.cyq.product.domain.ProductDo;
import com.cyq.product.model.CommonResult;
import com.cyq.product.model.PageParamVo;
import com.cyq.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 商品控制器
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private static final String FILE_UPLOAD_URI = File.separator + "usr" + File.pathSeparator + "app" + 
            File.separator + "tomcat" + File.separator + "apache-tomcat-8.5.63" + File.separator + "webapps" +
            File.separator + "images" + File.separator;

    // 文件访问的URL
    private static  final String FILE_URL_BASE = "http://192.144.229.245:8080/images/";
    
    @Autowired
    private ProductService productService;

    /**
     * 通过分页获取商品数据信息
     * @param pageParamVo
     * @return
     */
    @GetMapping("/get-all-products-page")
    public CommonResult getProductsForPgae(@RequestBody PageParamVo pageParamVo) {
        try {
            productService.getProductsForPgae(pageParamVo);
            return CommonResult.success(pageParamVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取商品信息失败...");
            return CommonResult.failed();
        }
    }

    /**
     * 通过分页获取商品数据信息
     * @param pageParamVo
     * @return
     */
    @GetMapping("/sys-product/get-all-products")
    public CommonResult getProducts(@RequestBody PageParamVo pageParamVo) {
        try {
            productService.getProducts(pageParamVo);
            return CommonResult.success(pageParamVo);
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
    @PostMapping("/sys-product/save-product")
    public CommonResult saveProduct(@RequestBody PageParamVo<ProductDo> pageParamVo) {
        try {
            productService.saveProduct(pageParamVo.getDatas());
            return getProducts(pageParamVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("保存商品信息失败");
            return CommonResult.failed();
        }
    }


    @GetMapping("/get-product")
    public CommonResult getProduct(String id) {
        try {
            ProductDo product = productService.getProduct(id);
            return CommonResult.success(product);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("获取商品信息失败");
        }
    }

    @GetMapping("/sys-product/upload")
    public CommonResult uploadFile(MultipartFile multipartFile, HttpServletRequest request) {
        try {

            // 原始文件名字
            String[] suffixNames = multipartFile.getOriginalFilename().split("\\.");
            String suffixName= suffixNames[suffixNames.length-1];
            String imageName = UUID.randomUUID().toString().replace("-", "") + "." + suffixName;

            LocalDateTime dateTime = LocalDateTime.now();
            int day = dateTime.getDayOfWeek().getValue();
            String tmpFile = "images" + day + File.separator + imageName;
            String tmpUrl = "images" + day + "/" + imageName;
            // 存储文件的位置
            File targetFile = new File(FILE_UPLOAD_URI + tmpFile);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            // 开始文件上传
            InputStream inputStream = multipartFile.getInputStream();
            FileOutputStream fos = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            inputStream.close();
            return CommonResult.success(FILE_URL_BASE + tmpUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("文件上传失败");
        }
    }

}
