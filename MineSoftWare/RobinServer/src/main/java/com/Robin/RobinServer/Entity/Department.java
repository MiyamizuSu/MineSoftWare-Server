package com.Robin.RobinServer.Entity;

public class Department {
    private int departmentId;
    private String departmentName;
    private String departmentPerson;
    private String personTel;
    private String departmentState; //0: 停用，1:正常
    private String upper;
    private String createTime;

    public Department() {}

    public Department(int departmentId, String departmentName, String departmentPerson, String personTel, String departmentState, String upper, String createTime) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentPerson = departmentPerson;
        this.personTel = personTel;
        this.departmentState = departmentState;
        this.upper = upper;
        this.createTime = createTime;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPersonTel() {
        return personTel;
    }

    public void setPersonTel(String personTel) {
        this.personTel = personTel;
    }

    public String getDepartmentPerson() {
        return departmentPerson;
    }

    public void setDepartmentPerson(String departmentPerson) {
        this.departmentPerson = departmentPerson;
    }

    public String getDepartmentState() {
        return departmentState;
    }

    public void setDepartmentState(String departmentState) {
        this.departmentState = departmentState;
    }

    public String getUpper() {
        return upper;
    }

    public void setUpper(String upper) {
        this.upper = upper;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
