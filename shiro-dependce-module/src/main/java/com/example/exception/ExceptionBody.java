package com.example.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 11:46
 * @Version 1.0
 */
@Data
@Builder
public class ExceptionBody {
    @ApiModelProperty(value = "请求路径")
    private String path;
    @ApiModelProperty(value = "错误码")
    private String code;
    @ApiModelProperty(value = "请求方法")
    private String method;
    @ApiModelProperty(value = "错误信息")
    private String message;
}
