package com.example.exception;

import java.util.stream.Stream;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 12:52
 * @Version 1.0
 */
public enum ExceptionEnum {
    ERROR_SYSTEM("服务内部错误","517001"),
    NO_AUTHENTICATION("无权限","517002"),
    ;
    private String message;
    private String code;
    ExceptionEnum(String code,String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
    public static String getValues(String code){
        ExceptionEnum exceptionEnum = Stream.of(ExceptionEnum.values()).filter(s -> s.getCode().equals(code)).findAny().orElseGet(null);
        return exceptionEnum.getMessage();
    }
}
