package com.example.annotation;

import com.example.shiroconfig.ShiroConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 14:21
 * @Version 1.0
 */
@Import(ShiroConfig.class)
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ShiroAnnotation {
}
