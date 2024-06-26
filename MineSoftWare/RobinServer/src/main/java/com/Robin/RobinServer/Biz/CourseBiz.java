package com.Robin.RobinServer.Biz;


import com.Robin.RobinServer.Entity.Course;
import com.Robin.RobinServer.Mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseBiz {
    @Autowired
    private CourseMapper mapper;

    public String insertCourse(Course course) {
        try {
            mapper.insertNewCourse(course);
            return "200";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "777";
        }
    }
}
