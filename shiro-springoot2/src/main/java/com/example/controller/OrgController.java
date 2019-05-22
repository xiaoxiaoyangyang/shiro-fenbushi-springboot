package com.example.controller;

import com.example.entity.Org;
import com.example.entity.ResponseMessage;
import com.example.service.OrgService;
import com.example.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/21 18:31
 * @Version 1.0
 */
@RestController
public class OrgController {
    @Autowired
    private OrgService orgService;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @GetMapping("/showOrg")
    public ResponseEntity<ResponseMessage> showAll(@RequestHeader("Authorization")String token){
        List<Org> orgs = orgService.selectAll();
        String username = JWTUtil.getUsername(token);
        String s = redisTemplate.opsForValue().get(username);
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setList(orgs);
        responseMessage.setToken(s);
        ResponseEntity<ResponseMessage> listResponseEntity = new ResponseEntity<>(responseMessage, HttpStatus.OK);
        return listResponseEntity;
    }
}
