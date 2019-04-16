package com.xmcc.springboot_demo.common;

import lombok.Getter;

/**
 * 返回值接口都会用到成功失败等相同的数据，
 * 所以我们把常用的提成枚举类，增加系统可扩展性
 */
@Getter
public enum ResultEnums {
    SUCCESS(0,"成功"),
    FAIL(1,"失败"),
    PRODUCT_UP(0,"正常"),
    PRODUCT_DOWN(1,"商品下架"),
    PRODUCT_NOT_ENOUGH(10,"商品库存不足"),
    PARAM_ERROR(1,"参数异常"),
    NOT_EXITS(1,"商品不存在");
    private int code;
    private String msg;

    ResultEnums(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
