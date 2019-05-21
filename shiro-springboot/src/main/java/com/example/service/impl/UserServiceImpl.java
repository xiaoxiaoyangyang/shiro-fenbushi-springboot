package com.example.service.impl;

import com.example.entity.User;
import com.example.mapper.ShiroUserMapper;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 15:06
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ShiroUserMapper shiroUserMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean selectOne(String username, String password) {
        Md5Hash omgg = new Md5Hash(password, "OMGG", 1024);
        User user = userMapper.selectOne(username);
        if(user!=null){
            if(user.getPassword().equals(omgg.toString())){
                return true;
            }
        }
        return false;
    }


    @Override
    public Integer insert(User user) {
        Integer insert = shiroUserMapper.insert(user);
        return insert;
    }
}
