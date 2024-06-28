package com.Robin.RobinServer.Controller;

import com.Robin.RobinServer.Biz.CompanyUserBiz;
import com.Robin.RobinServer.Biz.CourseBiz;
import com.Robin.RobinServer.Entity.Course;
import com.Robin.RobinServer.Util.RobinUtil;
import com.Robin.RobinServer.Util.Signature;
import com.Robin.RobinServer.ViewEntity.CompanyUser_View;
import com.Robin.RobinServer.ViewEntity.SuperAdmin_View;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/Course")
public class CourseController {

    @Autowired
    private CourseBiz courseBiz;

    @Autowired
    private CompanyUserBiz companyUserBiz;

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/addNewCourse")
    public Map addNewCourse(@RequestBody Course course,HttpServletRequest httpServletRequest) {
        CompanyUser_View companyUserView =(CompanyUser_View)httpServletRequest.getSession().getAttribute("user");
        String userName= companyUserView.getUserName();
        Map<String,String> response= new HashMap<>();
        course.setBelongCompany(companyUserView.getBelongCompany());
        response.put("statusCode",courseBiz.insertCourse(course));
        return response;
    }

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/getExcel")
    public Map getExcel(@RequestBody List<Course> courses, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException {
        RobinUtil.setDownLoadHead("course",httpServletResponse);
        EasyExcel.write(httpServletResponse.getOutputStream(), Course.class)
                .autoCloseStream(Boolean.FALSE)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("课程信息")
                .doWrite(courses);
        return null;
    }

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/getSignature")
    public Map getSignature(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception {
        Map<String,String> response= new HashMap<>();
        Signature sign = new Signature();
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 12);
        String signature = sign.getUploadSignature();
        response.put("signature", signature);
        return response;
    }
    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/list")
    public Map getCourseList(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception {
        Map<String,Object> response= new HashMap<>();
        if(httpServletRequest.getSession().getAttribute("user").getClass().equals(CompanyUser_View.class)){
            CompanyUser_View companyUserView =(CompanyUser_View)httpServletRequest.getSession().getAttribute("user");
            String userName= companyUserView.getUserName();
            String belongCompany=companyUserView.getBelongCompany();
            response.put("courses",courseBiz.listCourse(belongCompany));
        }
        else if(httpServletRequest.getSession().getAttribute("user").getClass().equals(SuperAdmin_View.class)){
            response.put("courses",courseBiz.listCourse());
        }
        return response;
    }
}
