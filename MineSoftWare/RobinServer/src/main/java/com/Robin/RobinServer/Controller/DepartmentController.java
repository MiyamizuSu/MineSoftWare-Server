package com.Robin.RobinServer.Controller;

import com.Robin.RobinServer.Biz.ConferenceBiz;
import com.Robin.RobinServer.Biz.DepartmentBiz;
import com.Robin.RobinServer.Entity.Conference;
import com.Robin.RobinServer.Entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentBiz departmentBiz;

    public void setDepartmentBiz(DepartmentBiz departmentBiz) {
        this.departmentBiz = departmentBiz;
    }

    @RequestMapping("/listAll")
    public Map<String, Object> getDepartmentList() {
        Map<String, Object> response = new HashMap<>();
        List<Department> departmentList = departmentBiz.getAllDepartments();
        response.put("isOk", true);
        response.put("departmentList", departmentList);
        return response;
    }

    @RequestMapping("/listByUpper")
    public Map<String, Object> listDepartmentsByUpper(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        String upper = (String) request.get("upper");
        List<Department> departmentList = departmentBiz.getDepartmentsByUpper(upper);
        if (departmentList != null && !departmentList.isEmpty()) {
            response.put("isOk", true);
            response.put("departmentList", departmentList);
            response.put("msg", "查询成功");
        } else {
            response.put("isOk", false);
            response.put("departmentList", null);
            response.put("msg", "查询失败，请检查departmentId");
        }
        return response;
    }

    @RequestMapping("/getById")
    public Map<String, Object> getDepartmentById(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        int departmentId = (int) request.get("departmentId");
        Department department = departmentBiz.getDepartmentById(departmentId);
        if (department != null) {
            response.put("isOk", true);
            response.put("department", department);
            response.put("msg", "查询成功");
        } else {
            response.put("isOk", false);
            response.put("department", null);
            response.put("msg", "查询失败，请检查departmentId");
        }
        return response;
    }

    @RequestMapping("/getByName")
    public Map<String, Object> getDepartmentByName(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        String departmentName = (String) request.get("departmentName");
        List<Department> departmentList = departmentBiz.getDepartmentByName(departmentName);
        if (departmentList != null) {
            response.put("isOk", true);
            response.put("departmentList", departmentList);
            response.put("msg", "查询成功");
        } else {
            response.put("isOk", false);
            response.put("departmentList", null);
            response.put("msg", "查询失败，请检查departmentName");
        }
        return response;
    }

    @RequestMapping("/getByNameAndUpper")
    public Map<String, Object> getDepartmentByNameAndUpper(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        String departmentName = (String) request.get("departmentName");
        String upper = (String) request.get("upper");
        Department department = departmentBiz.getDepartmentByNameAndUpper(departmentName,upper);
        if (department != null) {
            response.put("isOk", true);
            response.put("department", department);
            response.put("msg", "查询成功");
        } else {
            response.put("isOk", false);
            response.put("department", null);
            response.put("msg", "查询失败，请检查departmentId");
        }
        return response;
    }

    public Map<String, Object> listDepartmentsByState(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        String departmentState = (String) request.get("departmentState");
        List<Department> departmentList = departmentBiz.getDepartmentsByState(departmentState);
        if (departmentList != null && !departmentList.isEmpty()) {
            response.put("isOk", true);
            response.put("departmentList", departmentList);
            response.put("msg", "查询成功");
        } else {
            response.put("isOk", false);
            response.put("departmentList", null);
            response.put("msg", "查询失败，请检查departmentId");
        }
        return response;
    }

    @RequestMapping("/add")
    public Map<String, Object> addDepartment(@RequestBody Department department) {
        Map<String, Object> response = new HashMap<>();
        Date dNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = sdf.format(dNow);
        department.setCreateTime(createTime);
        String statusCode = departmentBiz.addDepartment(department);
        response.put("statusCode", statusCode); // "200" / "601" / "777"
        return response;
    }

    @RequestMapping("/deleteById")
    public Map<String, Object> deleteById(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        int departmentId = (int) request.get("departmentId");
        boolean isOk = departmentBiz.removeDepartmentById(departmentId);
        if (isOk) {
            response.put("isOk", true);
            response.put("msg", "删除成功");
        } else {
            response.put("isOk", false);
            response.put("msg", "删除失败，请检查departmentId");
        }
        return response;
    }

    @RequestMapping("/update")
    public Map<String, Object> updateDepartment(@RequestBody Department updatedDepartment) {
        Map<String, Object> response = new HashMap<>();
        boolean isOk = departmentBiz.updateDepartment(updatedDepartment);
        if (isOk) {
            response.put("isOk", true);
            response.put("msg", "修改成功");
        } else {
            response.put("isOk", false);
            response.put("msg", "修改失败");
        }
        return response;
    }

}
