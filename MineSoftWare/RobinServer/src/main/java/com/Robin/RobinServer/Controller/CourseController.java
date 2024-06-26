package com.Robin.RobinServer.Controller;

import com.Robin.RobinServer.Biz.CourseBiz;
import com.Robin.RobinServer.Entity.Course;
import com.Robin.RobinServer.Util.RobinUtil;
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

@Controller
@RequestMapping("/Course")
public class CourseController {

    @Autowired
    private CourseBiz courseBiz;

    @CrossOrigin(origins = "http://localhost:5173/",allowCredentials = "true")
    @ResponseBody
    @RequestMapping("/addNewCourse")
    public Map addNewCourse(@RequestBody Course course) {
        Map<String,String> response=new HashMap<String,String>();
        response.put("statusCode",      courseBiz.insertCourse(course));
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
}
