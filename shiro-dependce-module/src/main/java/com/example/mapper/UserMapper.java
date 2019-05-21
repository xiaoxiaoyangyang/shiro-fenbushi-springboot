package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 13:33
 * @Version 1.0
 */
@Mapper
public interface UserMapper {
    User selectOne(String username);
    int insert(User user);
}
