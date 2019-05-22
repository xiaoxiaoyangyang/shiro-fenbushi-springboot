package com.example.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/22 11:10
 * @Version 1.0
 */
@Data
public class ResponseMessage
{
    private List<Org> list;
    private String token;
}
