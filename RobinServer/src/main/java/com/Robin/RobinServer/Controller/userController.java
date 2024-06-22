package com.Robin.RobinServer.Controller;

import com.Robin.RobinServer.Biz.companyBiz;
import com.Robin.RobinServer.Biz.companyUserBiz;
import com.Robin.RobinServer.Biz.superAdminBiz;
import com.Robin.RobinServer.Entity.company;
import com.Robin.RobinServer.Entity.companyUser;
import com.Robin.RobinServer.Entity.superAdmin;
import com.Robin.RobinServer.ViewEntity.companyUser_View;
import com.Robin.RobinServer.ViewEntity.superAdmin_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
public class userController {
    @Autowired
    private companyBiz companyBiz;

    @Autowired
    private companyUserBiz companyUserBiz;

    @Autowired
    private superAdminBiz  superAdminBiz;

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/user/login")
    public Map login( @RequestBody Map<String,String>request ,HttpServletRequest httpServletRequest) {
        Map<String,String> response = new HashMap<>();
        String userCompany= request.get("userCompany");
        String userName=request.get("userName");
        String userPassword=request.get("userPassword");
        if (userCompany.isEmpty()){
            superAdmin superAdmin=new superAdmin(userName,userPassword);
            response.put("statusCode", String.valueOf(superAdminBiz.superAdminLogin(superAdmin)));
            if (response.get("statusCode").equals("200")){
                superAdmin_View superAdminView=new superAdmin_View(superAdmin);
                httpServletRequest.getSession().setAttribute("user",superAdminView);
            }
            else {

            }
        }
        else {
            companyUser user = new companyUser(userName, userPassword, userCompany);
            response.put("statusCode", String.valueOf(companyUserBiz.companyUserLogin(user)));
            if (response.get("statusCode").equals("200")) {
                companyUser_View companyUserView=new companyUser_View(user);
                httpServletRequest.getSession().setAttribute("user", companyUserView);
            } else {

            }
        }
        return response;
    }

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/user/token")
    public Map getToken(@RequestBody Map<String,String> request, HttpServletRequest httpServletRequest) {
        Map<String,String> response = new HashMap<String,String>();
        httpServletRequest.getSession().setAttribute("token","1234");
        response.put("status", "success");
        /**
         * 暂时不启用，Token要钱
         */
//        int res= outerApi.AliCloudSms("13066696438");
//        if(res==200) {
//
//        }
//        else {
//
//        }
        return response;
    }


    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/user/register")
    public Map register(@RequestBody Map<String,String> request ,HttpServletRequest httpServletRequest) {
        Map<String,String> response = new HashMap<String,String>();
        String username = request.get("userName");
        String password = request.get("userPassword");
        String companyName=request.get("companyName");
        String companyPhoneNumber=request.get("companyPhoneNumber");
        String userRealName=request.get("userRealName");
        System.out.println(userRealName);
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        companyUser newUser= new companyUser(username,password,companyName,companyPhoneNumber,userRealName,timestamp,true);
        System.out.println(newUser.getUserRealName());
        company newCompany=new company(companyName,username);
        String token=request.get("token");
        if (httpServletRequest.getSession().getAttribute("token").equals(token)) {
            companyBiz.insertCompany(newCompany);
            response.put("statusCode",String.valueOf(companyUserBiz.insertNewCompanyUser(newUser)));
        }
        else {
            response.put("statusCode","777");
        }
       return response;
    }

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/user/loading")
    public Map Loading(HttpServletRequest httpServletRequest) {
        Map<String,Object> response = new HashMap<>();
        if (httpServletRequest.getSession().getAttribute("user").getClass().equals(companyUser_View.class)){
            companyUser_View user = (companyUser_View) httpServletRequest.getSession().getAttribute("user");
            companyUser_View back = companyUserBiz.loadingData(user);
            response.put("userData", back );
        }
        else {

        }
        return response;
    }

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/checkSession")
    public Map checkSession(HttpServletRequest httpServletRequest) {
        Map<String,Object> response = new HashMap<>();
        if (httpServletRequest.getSession().getAttribute("user")!=null){
            response.put("statusCode", 200 );
        }
        else {
            response.put("statusCode", 404 );
        }
        return response;
    }

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/user/updateMessage")
    public Map updateMessage( @RequestBody Map <String,String> request ,HttpServletRequest httpServletRequest) {
        Map<String,String> response = new HashMap<>();
        String userName=request.get("userName");
        String userPhoneNumber=request.get("userPhoneNumber");
        String userEmail=request.get("userEmail");
        companyUser_View user = (companyUser_View) httpServletRequest.getSession().getAttribute("user");
        user.setUserName(userName);
        user.setUserPhoneNumber(userPhoneNumber);
        user.setUserEmail(userEmail);
        response.put("status",String.valueOf(companyUserBiz.updateCompanyUser(user)));
        return response;
    }

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/user/updatePassword")
    public Map upadatePsssword(@RequestBody Map<String,String> request ,HttpServletRequest httpServletRequest) {
        Map<String,String> response = new HashMap<>();
        String newPassword=request.get("newPassword");
        String userPhoneNumber=request.get("userPhoneNumber");
        companyUser_View user = (companyUser_View) httpServletRequest.getSession().getAttribute("user");
        response.put("statusCode",String.valueOf( companyUserBiz.updatePassword(user.getUserName(), newPassword))) ;
        return response;
        //        int res= outerApi.AliCloudSms(userPhoneNumber");
//        if(res==200) {
//
//        }
//        else {
//
//        }
    }
}
