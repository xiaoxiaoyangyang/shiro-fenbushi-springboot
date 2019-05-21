package com.example;

import com.example.annotation.ShiroAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 10:25
 * @Version 1.0
 */
@ShiroAnnotation
@SpringBootApplication
public class ShiroSpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroSpringbootApplication.class,args);
    }
}
