package com.Robin.RobinServer.Controller;


import com.Robin.RobinServer.Biz.companyBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class companyController  {

    @Autowired
    private companyBiz companyBiz;

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/company/allCompany")
    public Map getAllCompany(HttpServletRequest httpServletRequest) {
        Map<String, Object> response = new HashMap<String,Object>();
        response.put("data",companyBiz.getAllCompanyName());
        return response;
    }

}
