package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 14:48
 * @Version 1.0
 */
@Api
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @ApiOperation(value = "/user/mylogin")
    @GetMapping("/user/mylogin")
    public ResponseEntity<Boolean> login(@ModelAttribute User user){
        boolean b = userService.selectOne(user.getName(), user.getPassword());
        ResponseEntity<Boolean> booleanResponseEntity = new ResponseEntity<>(b, HttpStatus.OK);
        return booleanResponseEntity;
    }
    @ApiOperation(value = "/user/add")
    @RequiresPermissions("/user/add")
    @PostMapping("/user/add")
    public ResponseEntity<Integer> add(@RequestBody User user){
        Integer insert = userService.insert(user);
        return new ResponseEntity<Integer>(insert,HttpStatus.OK);

    }
}
