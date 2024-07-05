package com.Robin.RobinServer.Biz;

import com.Robin.RobinServer.Entity.Company;
import com.Robin.RobinServer.Entity.Department;
import com.Robin.RobinServer.Mapper.CompanyMapper;
import com.Robin.RobinServer.Mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentBiz {
    @Autowired
    private DepartmentMapper mapper1;

    public void setMapper(DepartmentMapper mapper) {
        this.mapper1 = mapper;
    }

    public List<Department> getAllDepartments() {
        return mapper1.allDepartments();
    }

    public Department getDepartmentById(int departmentId) {
        return mapper1.selectDepartmentById(departmentId);
    }

    public Department getDepartmentByNameAndUpper(String departmentName,String upper) {
        return mapper1.selectDepartmentByNameAndUpper(departmentName,upper);
    }

    public List<Department> getDepartmentByName(String departmentName) {
        return mapper1.selectDepartmentByName(departmentName);
    }

    public List<Department> getDepartmentsByState(String departmentState) {
        return mapper1.selectDepartmentsByState(departmentState);
    }

    public List<Department> getDepartmentsByUpper(String upper) {
        return mapper1.selectDepartmentsByUpper(upper);
    }

    public String addDepartment(Department department) {
        try {
            boolean isDistinct = true;
            Department dbDepartment = mapper1.selectDepartmentByNameAndUpper(department.getDepartmentName(), department.getUpper());
            if (dbDepartment != null) {
                isDistinct = false;
            }
            if (isDistinct) {
                System.out.println("将要insert的Department："+department);
                mapper1.insertDepartment(department);
                return "200";
            }
            else {
                return "601"; //部门名称重复
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "777";
        }
    }

    public boolean removeDepartmentById(int departmentId) {
        return mapper1.deleteDepartmentById(departmentId) > 0;
    }

    public boolean updateDepartment(Department updatedDepartment) {
        return mapper1.updateDepartment(updatedDepartment) > 0;
    }
}
