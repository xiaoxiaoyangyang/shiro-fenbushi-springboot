package com.example.mapper;

import com.example.entity.Org;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/21 18:36
 * @Version 1.0
 */
@Mapper
public interface OrgMapper {
    List<Org> selectAll();
}
