package com.cyq.product.controller;

import com.cyq.product.domain.ProductDo;
import com.cyq.product.model.CommonResult;
import com.cyq.product.model.PageParamVo;
import com.cyq.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${imagesStorageLocation}")
    private String FILE_UPLOAD_URI_BASE;

    // 文件访问的URL
    @Value("${imagesStorageUrl}")
    private String FILE_URL_BASE;
    
    @Autowired
    private ProductService productService;

    /**
     * 通过分页获取商品数据信息
     * @param pageParamVo
     * @return
     */
    @PostMapping("/get-all-products-page")
    public CommonResult getProductsForPgae(@RequestBody PageParamVo<ProductDo> pageParamVo) {
        try {
            PageParamVo datas = productService.getProductsForPgae(pageParamVo);
            return CommonResult.success(datas);
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
    @PostMapping("/sys-product/get-all-products")
    public CommonResult getProducts(@RequestBody PageParamVo<ProductDo> pageParamVo) {
        try {
            PageParamVo products = productService.getProducts(pageParamVo);
            return CommonResult.success(products);
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
            pageParamVo.setDatas(null);
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

    @GetMapping("/sys-product/del")
    public CommonResult deleteProduct(String id) {
        try {
            productService.delAndPublishProduct(id, 1, 2);
            return CommonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed();
        }
    }

    @GetMapping("/sys-product/pub")
    public CommonResult publishProduct(@RequestParam("id") String id, @RequestParam("opVal") Integer opVal) {
        try {
            productService.delAndPublishProduct(id, 2, opVal);
            return CommonResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed();
        }
    }

    @PostMapping("/sys-product/upload")
    public CommonResult uploadFile(MultipartFile file, HttpServletRequest request) {
        try {
            String FILE_UPLOAD_URI = FILE_UPLOAD_URI_BASE.replace("\\/", File.separator);
            // 原始文件名字
            String[] suffixNames = file.getOriginalFilename().split("\\.");
            String suffixName= suffixNames[suffixNames.length-1];
            String imageName = UUID.randomUUID().toString().replace("-", "") + "." + suffixName;

            LocalDateTime dateTime = LocalDateTime.now();
            int day = dateTime.getDayOfWeek().getValue();
            String tmpFile = "images" + day;
            String tmpUrl = "images" + day + "/" + imageName;
            // 存储文件的位置
            File targetFile = new File(FILE_UPLOAD_URI + tmpFile);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            // 开始文件上传
            InputStream inputStream = file.getInputStream();
            FileOutputStream fos = new FileOutputStream(FILE_UPLOAD_URI + tmpFile + File.separator + imageName);
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
