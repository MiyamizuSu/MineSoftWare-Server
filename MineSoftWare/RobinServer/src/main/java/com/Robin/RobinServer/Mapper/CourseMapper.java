package com.Robin.RobinServer.Mapper;

import com.Robin.RobinServer.Entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseMapper {

    @Insert("insert into robin_course values (#{courseCode},#{courseName},#{courseIntroduction},#{imgUrl},#{courseMedia},#{courseCompare},#{author})")
    public void insertNewCourse(Course course);
}
