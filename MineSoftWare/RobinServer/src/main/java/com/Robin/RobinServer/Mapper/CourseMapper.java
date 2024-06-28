package com.Robin.RobinServer.Mapper;

import com.Robin.RobinServer.Entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CourseMapper {

    @Insert("insert into robin_course values (#{courseCode},#{courseName},#{courseIntroduction},#{imgUrl},#{courseMediaUrl},#{courseCompare},#{author},#{belongCompany})")
    public void insertNewCourse(Course course);

    @Select("select * from robin_course where belongCompany=#{companyName}")
    public ArrayList<Course> getCourseByCompany(String companyName);

    @Select("SELECT * from robin_course")
    public ArrayList<Course> getAllCourses();
    

}
