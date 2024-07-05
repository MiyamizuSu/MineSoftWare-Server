package com.Robin.RobinServer.Mapper;

import com.Robin.RobinServer.Entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    @Select("select * from department")
    List<Department> allDepartments();

    @Select("select * from department where departmentId=#{departmentId}")
    Department selectDepartmentById(int departmentId);

    @Select("select * from department where departmentName=#{departmentName}")
    List<Department> selectDepartmentByName(String departmentName);

    @Select("select * from department where upper=#{upper}")
    List<Department> selectDepartmentsByUpper(String upper);

    @Select("select * from department where departmentName=#{departmentName} and upper=#{upper}")
    Department selectDepartmentByNameAndUpper(@Param("departmentName") String departmentName, @Param("upper") String upper);

    @Select("select * from department where departmentState=#{departmentState}")
    List<Department> selectDepartmentsByState(String departmentState);

    @Insert("insert into department values (null, #{departmentName}, #{departmentPerson}, #{personTel}, #{departmentState}, #{upper}, #{createTime})")
    int insertDepartment(Department department);

    @Delete("delete from department where departmentId=#{departmentId}")
    int deleteDepartmentById(int departmentId);

    @Update("update department set departmentName=#{departmentName}, departmentPerson=#{departmentPerson}, personTel=#{personTel}, departmentState=#{departmentState}, upper=#{upper} where departmentId=#{departmentId}")
    int updateDepartment(Department updatedDepartment);
}
