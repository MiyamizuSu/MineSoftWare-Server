package com.Robin.RobinServer.service.impl;

import com.Robin.RobinServer.Entity.page.CompanyQuery;
import com.Robin.RobinServer.Entity.CompanyUser;
import com.Robin.RobinServer.ViewEntity.CompanyVo;
import com.Robin.RobinServer.Mapper.CompanyUserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.Robin.RobinServer.service.CompanyUserService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author HUAWEI
* @description 针对表【robin_companyuser】的数据库操作Service实现
* @createDate 2024-06-29 18:11:51
*/
@Service
public class CompanyUserServiceImpl extends ServiceImpl<CompanyUserMapper, CompanyUser>
    implements CompanyUserService {
    @Override
    public IPage<CompanyVo> findAllCompany(Page<CompanyVo> page, CompanyQuery pageBean) {
        return baseMapper.findAllCompany(page, pageBean);
    }
}




