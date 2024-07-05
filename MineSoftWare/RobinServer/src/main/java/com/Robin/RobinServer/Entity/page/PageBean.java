package com.Robin.RobinServer.Entity.page;

import lombok.Data;

/**
 * 分页Model类
 */
@Data
public class PageBean {


    private int pageNum; // 第几页
    private int pageSize; // 每页记录数
    private int start;  // 起始页
    private String query; // 查询参数
}
