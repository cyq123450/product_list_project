package com.cyq.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 结果统一响应实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;
    // 响应数据
    private T datas;

    public static <T> CommonResult<T> success() {
        return success(null);
    }

    public static <T> CommonResult<T> success(T datas) {
        return success(ResultCode.SUCCESS.getValue(), datas);
    }

    public static <T> CommonResult success(String message, T datas) {
        return success(ResultCode.SUCCESS.getCode(), message, datas);
    }

    public static <T> CommonResult success(Integer code, String message, T dadas) {
        return new CommonResult(code, message, dadas);
    }

    public static <T> CommonResult failed() {
        return failed(ResultCode.FAILED.getValue());
    }

    public static <T> CommonResult failed(String message) {
        return failed(ResultCode.FAILED.getCode(), message);
    }

    public static <T> CommonResult failed(Integer code, String message) {
        return new CommonResult(code, message, null);
    }

}
