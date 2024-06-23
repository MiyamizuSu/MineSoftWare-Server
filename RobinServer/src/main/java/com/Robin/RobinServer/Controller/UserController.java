package com.Robin.RobinServer.Controller;

import com.Robin.RobinServer.Biz.CompanyBiz;
import com.Robin.RobinServer.Biz.CompanyUserBiz;
import com.Robin.RobinServer.Biz.SuperAdminBiz;
import com.Robin.RobinServer.Entity.Company;
import com.Robin.RobinServer.Entity.CompanyUser;
import com.Robin.RobinServer.Entity.SuperAdmin;
import com.Robin.RobinServer.Entity.User;
import com.Robin.RobinServer.ViewEntity.CompanyUser_View;
import com.Robin.RobinServer.ViewEntity.SuperAdmin_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CompanyBiz companyBiz;

    @Autowired
    private CompanyUserBiz companyUserBiz;

    @Autowired
    private SuperAdminBiz superAdminBiz;

//    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/login")
    public Map login( @RequestBody Map<String,String>request ,HttpServletRequest httpServletRequest) {
        Map response = new HashMap();
        String userCompany= request.get("userCompany");
        String userName=request.get("userName");
        String userPassword=request.get("userPassword");
        System.out.println("前端传入的公司名称："+userCompany);
        System.out.println("前端传入的用户名："+userName);
        System.out.println("前端传入的密码："+userPassword);
        if (userCompany.isEmpty()){
//            SuperAdmin superAdmin = new SuperAdmin(userName, userPassword);
//            response.put("statusCode", String.valueOf(superAdminBiz.superAdminLogin(userName, userPassword)));
//            if (response.get("statusCode").equals("200")){
//                SuperAdmin_View superAdminView=new SuperAdmin_View(superAdmin);
//                httpServletRequest.getSession().setAttribute("user",superAdminView);
//            }
//            else {
//
//            }
            SuperAdmin dbAdmin = superAdminBiz.superAdminLogin(userName, userPassword);
            if (dbAdmin != null) {
                SuperAdmin_View superAdminView = new SuperAdmin_View(dbAdmin);
                httpServletRequest.getSession().setAttribute("user", superAdminView);
                response.put("statusCode", "200");
                response.put("user", superAdminView);
                response.put("userType", "2"); //超级管理员类型
            }
            else {
                httpServletRequest.getSession().setAttribute("user", null);
                response.put("statusCode", "302");
                response.put("user", null);
                response.put("userType", "-1"); //登录失败
            }
        }
        else {
//            response.put("statusCode", String.valueOf(companyUserBiz.companyUserLogin(user)));
//            if (response.get("statusCode").equals("200")) {
//                CompanyUser_View companyUserView=new CompanyUser_View(user);
//                httpServletRequest.getSession().setAttribute("user", companyUserView);
//            } else {
//
//            }
            CompanyUser dbUser = companyUserBiz.companyUserLogin(userName, userPassword, userCompany);
            if (dbUser != null) {
                CompanyUser_View companyUserView = new CompanyUser_View(dbUser);
                httpServletRequest.getSession().setAttribute("user", companyUserView);
                response.put("statusCode", "200");
                response.put("user", companyUserView);
                response.put("userType", "1"); //企业管理员类型
            }
            else {
                httpServletRequest.getSession().setAttribute("user", null);
                response.put("statusCode", "302");
                response.put("user", null);
                response.put("userType", "-1"); //登录失败
            }
        }
        return response;
    }

//    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/token")
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


//    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/register")
    public Map register(@RequestBody Map<String,String> request ,HttpServletRequest httpServletRequest) {
        System.out.println("处理用户注册-----");
        Map response = new HashMap();
        String username = request.get("userName");
        String password = request.get("userPassword");
        String companyName=request.get("companyName");
        String companyPhoneNumber=request.get("companyPhoneNumber");
        String userRealName=request.get("userRealName");
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        //
        CompanyUser newUser= new CompanyUser(username, userRealName, password, companyPhoneNumber, companyName, "", timestamp);
        Company newCompany=new Company(companyName,username, "");
        //检测前端发来的验证码是否正确，当前验证码默认为"1234"
        String token=request.get("token");
        if (httpServletRequest.getSession().getAttribute("token").equals(token)) {
            companyBiz.insertCompany(newCompany); //将企业添加进数据库
            System.out.println("加入数据库的Company："+newCompany);
            String statusCode = companyUserBiz.insertNewCompanyUser(newUser);
            System.out.println("状态码："+statusCode);
            if (statusCode.equals("200")) {
                System.out.println("加入数据库的CompanyUser："+newUser);
            }
            response.put("statusCode", statusCode); //返回状态码
        }
        else {
            response.put("statusCode","777");
        }
       return response;
    }

//    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/loading") //只加载当前session中的user
    public Map Loading(HttpServletRequest httpServletRequest) {
        Map<String,Object> response = new HashMap<>();
        if (httpServletRequest.getSession().getAttribute("user").getClass().equals(CompanyUser_View.class)){
            CompanyUser_View user = (CompanyUser_View) httpServletRequest.getSession().getAttribute("user");
            // 为什么还要load呢？难道登录存session的时候不存数据库中的完整数据吗？
//            CompanyUser_View back = companyUserBiz.loadingData(user);
//            response.put("userData", back );
            response.put("userData", user ); //返回的对象的属性中已包含userType
        }
        else {
            SuperAdmin_View user = (SuperAdmin_View) httpServletRequest.getSession().getAttribute("user");
            response.put("userData", user );
        }
        return response;
    }

//    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    // 我觉得这个方法并不需要
    @ResponseBody
    @RequestMapping("/checkSession")
    public Map checkSession(HttpServletRequest httpServletRequest) {
        Map<String,Object> response = new HashMap<>();
        if (httpServletRequest.getSession().getAttribute("user")!=null){
            response.put("statusCode", "200" );
        }
        else {
            response.put("statusCode", "404" );
        }
        return response;
    }

    /*前端有原来对象的数据，将对象任意信息修改完成后直接将整个对象返回过来即可
     */
//    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/updateMessage")
    public Map updateMessage(@RequestBody CompanyUser updatedUser, HttpServletRequest httpServletRequest) {
        Map response = new HashMap(); //返回的Map不用限制类型
        System.out.println("执行/user/updateMessage方法-----");
        System.out.println("前端传入的User："+updatedUser);
        HttpSession session = httpServletRequest.getSession();
        if(updatedUser.getUserType()==0 || updatedUser.getUserType()==1 ){
            CompanyUser_View session_user = (CompanyUser_View) session.getAttribute("user");
            String userName_before = session_user.getUserName(); //获取到更改之前的用户名
            CompanyUser_View updated_userView = new CompanyUser_View(updatedUser);
            String statusCode = companyUserBiz.updateCompanyUser(updated_userView, userName_before);
            if (statusCode.equals("200")) {
                //更新session中的user
                session.setAttribute("user", updated_userView);
            }
            response.put("statusCode", statusCode);
        }
        else if (session.getAttribute("user").getClass().equals(SuperAdmin_View.class)){
            SuperAdmin_View session_admin = (SuperAdmin_View) session.getAttribute("user");
            String userName_before = session_admin.getUserName(); //获取到更改之前的用户名
            SuperAdmin_View updatedAdminView = new SuperAdmin_View(updatedUser);
            String statusCode = superAdminBiz.updateAdmin(updatedAdminView, userName_before);
            if (statusCode.equals("200")) {
                //更新session中的user
                session.setAttribute("user", updatedAdminView);
            }
            response.put("statusCode", statusCode);
        }

        return response;
    }

//    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/updatePassword")
    public Map upadatePsssword(@RequestBody Map<String,String> request ,HttpServletRequest httpServletRequest) {
        Map<String,String> response = new HashMap<>();
        String newPassword=request.get("newPassword");
        String userPhoneNumber=request.get("userPhoneNumber"); //这个手机号是要用来获取验证码的？那应该先发送验证码，再把用户输入的验证码也传到后端来呀
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user").getClass().equals(CompanyUser_View.class)) {
            CompanyUser_View user = (CompanyUser_View) session.getAttribute("user");
            response.put("statusCode",String.valueOf( companyUserBiz.updatePassword(user.getUserName(), newPassword)));
        }
        else if (session.getAttribute("user").getClass().equals(SuperAdmin_View.class)) {
            SuperAdmin_View user = (SuperAdmin_View) session.getAttribute("user");
            response.put("statusCode",String.valueOf( superAdminBiz.updatePassword(user.getUserName(), newPassword)));
        }

        return response;
        //        int res= outerApi.AliCloudSms(userPhoneNumber");
//        if(res==200) {
//
//        }
//        else {
//
//        }
    }

    @RequestMapping("/listAllCompanyUsers")
    public Map getAllUsers() {
        List<CompanyUser> companyUserList = companyUserBiz.getAllUserList();
        Map map = new HashMap();
        map.put("isOk", true);
        map.put("companyUserList", companyUserList);
        map.put("msg", "查询成功");
        return map;
    }

    @RequestMapping("/getCompanyUserByName")
    public Map getCompanyUserByName(@RequestBody Map<String,String> request) {
        String userName = request.get("userName");
        CompanyUser companyUser = companyUserBiz.getCompanyUserByName(userName);
        Map map = new HashMap();
        if (companyUser != null) {
            map.put("companyUser", companyUser);
            map.put("isOk", true);
            map.put("msg", "查询成功");
        } else {
            map.put("companyUser", null);
            map.put("isOk", false);
            map.put("msg", "查询失败，请检查用户昵称");
        }
        return map;
    }

    @RequestMapping("/getUsersByCompany")
    public Map getUsersByCompany(@RequestBody Map<String,String> request) {
        String companyName = request.get("companyName");
        List<CompanyUser> companyUserList = companyUserBiz.getUsersByCompanyName(companyName);
        Map map = new HashMap();
        map.put("companyUserList", companyUserList);
        if ( !companyUserList.isEmpty() ) {
            map.put("isOk", true);
            map.put("msg", "查询成功");
        } else {
            map.put("isOk", false);
            map.put("msg", "查询失败，请检查公司名称");
        }
        return map;
    }

}
