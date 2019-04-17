package com.xmcc.springboot_demo.exception;

import com.xmcc.springboot_demo.common.ResultEnums;

public class MyException extends RuntimeException {
    private int  code;
    public MyException() {
        super();
    }

    public MyException(int code,String message) {
        super(message);
        this.code = code;
    }

    public MyException(String message) {
        this(ResultEnums.FAIL.getCode(),message);
    }
}
