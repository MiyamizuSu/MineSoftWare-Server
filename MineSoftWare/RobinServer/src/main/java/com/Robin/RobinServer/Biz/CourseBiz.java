package com.Robin.RobinServer.Biz;


import com.Robin.RobinServer.Entity.Course;
import com.Robin.RobinServer.Mapper.CourseMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseBiz {
    @Autowired
    private CourseMapper mapper;

    public String insertCourse(Course course) {
        try {
            String courseCode = RandomStringUtils.randomAlphanumeric(10);
            course.setCourseCode(courseCode);
            mapper.insertNewCourse(course);
            return "200";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "777";
        }
    }
    public ArrayList<Course> listCourse(String companyName){
        ArrayList<Course> res= mapper.getCourseByCompany(companyName);
        return res;
    }
    public ArrayList<Course> listCourse(){
        ArrayList<Course> res= mapper.getAllCourses();
        return res;
    }
}
