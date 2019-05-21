package com.example.service;

import com.example.entity.User;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 15:04
 * @Version 1.0
 */
public interface UserService {
    boolean selectOne(String username, String password);

    Integer insert(User user);
}
