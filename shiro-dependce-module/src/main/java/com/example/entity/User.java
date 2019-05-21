package com.example.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.lang.annotation.Target;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 13:34
 * @Version 1.0
 */
@Data
@ToString
public class User {
    private Integer userId;
    private String name;
    private String password;
}
