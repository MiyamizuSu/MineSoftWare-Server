package com.Robin.RobinServer.service;

import com.Robin.RobinServer.Entity.page.CompanyQuery;
import com.Robin.RobinServer.Entity.CompanyUser;
import com.Robin.RobinServer.ViewEntity.CompanyVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
* @author HUAWEI
* @description 针对表【robin_companyuser】的数据库操作Service
* @createDate 2024-06-29 18:11:51
*/
public interface CompanyUserService extends IService<CompanyUser> {
    IPage<CompanyVo> findAllCompany(Page<CompanyVo> page, CompanyQuery pageBean);

}
