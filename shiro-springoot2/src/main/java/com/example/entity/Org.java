package com.example.entity;

import lombok.Data;

/**
 * @Author: guozhiyang_vendor
 * @Date: 2019/5/21 18:32
 * @Version 1.0
 */
@Data
public class Org {
    private Integer orgId;
    private Integer parentId;
    private String orgName;
    private String treePath;
}
