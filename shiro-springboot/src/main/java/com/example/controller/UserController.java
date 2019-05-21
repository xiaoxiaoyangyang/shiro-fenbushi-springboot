package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 14:48
 * @Version 1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/mylogin")
    public ResponseEntity<Map<String, String>> login(@ModelAttribute User user){
        boolean b = userService.selectOne(user.getName(), user.getPassword());
        Map<String, String> map = new HashMap<>();
        if(b){
            Md5Hash omgg = new Md5Hash(user.getPassword(), "OMGG", 1024);
            map.put("token",JWTUtil.sign(user.getName(),omgg.toString()));
            map.put("message",null);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            map.put("message","账号密码错误");
            map.put("token",null);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
    }
    @RequiresPermissions("/user/add")
    @PostMapping("/user/add")
    public ResponseEntity<Integer> add(@RequestBody User user){
        Integer insert = userService.insert(user);
        return new ResponseEntity<Integer>(insert,HttpStatus.OK);

    }
    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Map<String, String>> unauthorized() {
        Map<String, String> map = new HashMap<>();
        map.put("noauthen","没有权限");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
