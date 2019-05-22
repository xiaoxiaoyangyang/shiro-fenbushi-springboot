package com.example.service.impl;

import com.example.entity.Org;
import com.example.mapper.OrgMapper;
import com.example.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/21 18:41
 * @Version 1.0
 */
@Service
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgMapper orgMapper;
    @Override
    public List<Org> selectAll() {
        List<Org> orgs = orgMapper.selectAll();
        return orgs;
    }
}
