package com.example;

import com.example.annotation.ShiroAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 11:02
 * @Version 1.0
 */
@ShiroAnnotation
@SpringBootApplication
public class ShiroSpringoot2Application {
    public static void main(String[] args) {
        SpringApplication.run(ShiroSpringoot2Application.class,args);
    }
}
