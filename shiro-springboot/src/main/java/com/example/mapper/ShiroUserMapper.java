package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 17:27
 * @Version 1.0
 */
@Mapper
public interface ShiroUserMapper {
    Integer insert(User user);
}
