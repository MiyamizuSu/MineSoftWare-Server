package com.Robin.RobinServer.Controller;

import com.Robin.RobinServer.Biz.CompanyBiz;
import com.Robin.RobinServer.Entity.Company;
import com.Robin.RobinServer.Entity.CompanyUser;
import com.Robin.RobinServer.Entity.page.CompanyQuery;
import com.Robin.RobinServer.Entity.page.UserQuery;
import com.Robin.RobinServer.Util.Result;
import com.Robin.RobinServer.ViewEntity.CompanyVo;
import com.Robin.RobinServer.service.CompanyService;
import com.Robin.RobinServer.service.CompanyUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyBiz companyBiz;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyUserService companyUserService;


    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/listAll")
    public Map getAllCompany(HttpServletRequest httpServletRequest) {
        Map<String, Object> response = new HashMap<String,Object>();
        response.put("companyList",companyBiz.getAllCompanies());
        return response;
    }

    @RequestMapping("/getByName")
    public Map getCompanyByName(@RequestBody Map<String, String> request) {
        String companyName = request.get("companyName");
        Company company = companyBiz.getCompanyByName(companyName);
        Map map = new HashMap();
        if (company != null) {
            map.put("company", company);
            map.put("isOk", true);
            map.put("msg", "查询成功");
        } else {
            map.put("company", null);
            map.put("isOk", false);
            map.put("msg", "查询失败，请检查企业名");
        }
        return map;
    }

    //根据条件分页查询租户信息
    @PostMapping("/listCompanyInfo")
    public Result listCompanyInfo(@RequestBody CompanyQuery pageBean) {
        if (!pageBean.getCurrentUser().equals("admin")){
            return Result.error("您没有权限");
        }
        Page<CompanyVo> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        IPage<CompanyVo> companyPage = companyUserService.findAllCompany(page, pageBean);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("companyList", companyPage.getRecords());
        resultMap.put("total", companyPage.getTotal());
        return Result.success(resultMap);
    }
    @PostMapping("/getCompanyName")
    public Result getCompanyName(@RequestBody UserQuery pageBean) {
        String userName = pageBean.getCurrentUser();
        Map<String, Object> resultMap = new HashMap<>();
        if (userName.equals("admin")){
            resultMap.put("companyList",companyBiz.getAllCompanies());
        }
        else {
            CompanyUser user = companyUserService.getOne(new QueryWrapper<CompanyUser>().lambda().eq(CompanyUser::getUserName,userName));
            LambdaQueryWrapper<Company> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Company::getCompanyName,user.getBelongCompany());
            List<Company> companyList = companyService.list(wrapper);
            resultMap.put("companyList",companyList);
        }
        return Result.success(resultMap);
    }
    @PostMapping("/addCompany")
    public Result addCompany(@RequestBody CompanyVo companyVo){
        String companyName = companyVo.getCompanyName();
        String userName = companyVo.getUserName();
        //判断companyName是否已注册
        if(companyService.getOne(new QueryWrapper<Company>().lambda().eq(Company::getCompanyName,companyName))!= null){
            return Result.error("企业已注册");
        }
        if(companyUserService.getOne(new QueryWrapper<CompanyUser>().lambda().eq(CompanyUser::getUserName,userName)) != null) {
            return Result.error("该用户名已存在");
        }
        Company company = new Company();
        company.setCompanyName(companyName);
        company.setConnectorName(companyVo.getConnectorName());
        company.setImgUrl(companyVo.getImgUrl());
        companyService.save(company);
        CompanyUser user = new CompanyUser();
        user.setUserName(userName);
        user.setUserPassword("123456");
        user.setBelongCompany(companyName);
        user.setUserType(1);
        user.setUserPhoneNumber(companyVo.getUserPhoneNumber());
        companyUserService.save(user);
        return Result.success("新增租户成功");
    }
    @PostMapping("/batchDeleteCompany")
    public Result batchDeleteUser(@RequestBody List<Company> companys) {
        for (Company company : companys) {
            companyService.remove(new LambdaQueryWrapper<>(company));
            LambdaQueryWrapper<CompanyUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(CompanyUser::getBelongCompany,company.getCompanyName());
            List<CompanyUser> companyUsers = companyUserService.list(wrapper);
            for (CompanyUser companyUser : companyUsers) {
                companyUser.setUserType(0);
                companyUser.setBelongCompany("已注销");
                companyUserService.update(companyUser,new QueryWrapper<CompanyUser>().eq("userName",companyUser.getUserName()));
            }
        }
        return Result.success("删除成功");
    }

    @PostMapping("/updateCompany")
    public Result updateUser(@RequestBody CompanyVo companyVo) {
        Company company = new Company(companyVo.getCompanyName(),companyVo.getConnectorName(), companyVo.getImgUrl());
        CompanyUser companyUser = companyUserService.getOne(new QueryWrapper<CompanyUser>().lambda().eq(CompanyUser::getUserName,companyVo.getUserName()));
        companyUser.setUserPhoneNumber(companyVo.getUserPhoneNumber());
        companyService.update(company,new QueryWrapper<Company>().lambda().eq(Company::getCompanyName,companyVo.getCompanyName()));
        companyUserService.update(companyUser,new QueryWrapper<CompanyUser>().lambda().eq(CompanyUser::getUserName,companyVo.getUserName()));
        return Result.success("更新成功");
    }
}
