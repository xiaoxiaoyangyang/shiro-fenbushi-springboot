package com.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/5 11:27
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {
    @Autowired
    private HttpServletRequest request;

//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handlerOpenDoorException(AuthenticationException openDoorException){
        ExceptionBody build = ExceptionBody.builder().path(request.getRequestURI())
                .code(ExceptionEnum.NO_AUTHENTICATION.getCode())
                .message(openDoorException.getMessage())
                .method(request.getMethod()).build();
        return new ResponseEntity<>(build,HttpStatus.BAD_REQUEST);
    }

//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseEntity<?> handleShiroException(ShiroException ex){
        ExceptionBody build = ExceptionBody.builder().path(request.getRequestURI())
                .code(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                .message(ex.getMessage())
                .method(request.getMethod()).build();
        return new ResponseEntity<>(build,HttpStatus.UNAUTHORIZED);
    }

    /**
     * 服务内部错误时所
     * @param ex
     * @return
     */
//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        log.info("错误信息详情:------------>{}",ex.getMessage());
        ex.printStackTrace();
        ExceptionBody build = ExceptionBody.builder().code(ExceptionEnum.ERROR_SYSTEM.getCode())
                .message(ExceptionEnum.ERROR_SYSTEM.getMessage())
                .path(request.getContextPath())
                .method(request.getMethod())
                .build();
        return new ResponseEntity<>(build,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
