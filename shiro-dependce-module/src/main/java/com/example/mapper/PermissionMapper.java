package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/20 12:58
 * @Version 1.0
 */
@Mapper
public interface PermissionMapper {
    List<String> selectAllPermission(String username);
}
