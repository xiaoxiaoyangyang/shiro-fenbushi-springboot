package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.JWTUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 14:48
 * @Version 1.0
 */
@RestController
public class UserController {
    //这是我用来测试版本回滚的
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/user/mylogin")
    public ResponseEntity<Map<String, String>> login(@ModelAttribute User user){
        boolean b = userService.selectOne(user.getName(), user.getPassword());
        Map<String, String> map = new HashMap<>(16);
        if(b){
            Md5Hash omgg = new Md5Hash(user.getPassword(), "OMGG", 1024);
            String sign = JWTUtil.sign(user.getName(), omgg.toString());
            map.put("token",sign);
            map.put("message",null);
            //登陆成功将username作为键token作为值传入redis中
            redisTemplate.opsForValue().set(user.getName(),sign,30,TimeUnit.MINUTES);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }else{
            map.put("message","账号密码错误");
            map.put("token",null);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }
    }
    @RequiresPermissions("/user/add")
    @PostMapping("/user/add")
    public ResponseEntity<Map<String, String>> add(@RequestBody User user,@RequestHeader("Authorization")String token){
        userService.insert(user);
        String username = JWTUtil.getUsername(token);
        String s = redisTemplate.opsForValue().get(username);
        Map<String,String> map=new HashMap<String,String>(16);
        map.put("Authorization",s);
        return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);

    }
    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Map<String, String>> unauthorized() {
        Map<String, String> map = new HashMap<>();
        map.put("noauthen","没有权限");
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
