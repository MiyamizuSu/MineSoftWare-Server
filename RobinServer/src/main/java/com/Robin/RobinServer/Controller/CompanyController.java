package com.Robin.RobinServer.Controller;


import com.Robin.RobinServer.Biz.CompanyBiz;
import com.Robin.RobinServer.Entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyBiz companyBiz;

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

}
