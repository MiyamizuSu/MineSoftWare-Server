package com.Robin.RobinServer.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public class CompanyUser extends User{
    private String belongCompany;
    private String belongDept;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp startTime;

    public CompanyUser() {
        super();
        this.setUserType(1);
    }

    public CompanyUser(String userName, String userRealName, String userPassword, String userPhoneNumber, String userEmail, String imgUrl, String belongCompany, String belongDept, Timestamp startTime) {
        super(userName, userRealName, userPassword, userPhoneNumber, userEmail, imgUrl, 1);
        this.belongCompany = belongCompany;
        this.belongDept = belongDept;
        this.startTime = startTime;
    }

    public CompanyUser(String userName, String userRealName, String userPassword, String userPhoneNumber, String belongCompany, String belongDept, Timestamp startTime) {
        super(userName, userRealName, userPassword, userPhoneNumber, 1);
        this.belongCompany = belongCompany;
        this.belongDept = belongDept;
        this.startTime = startTime;
    }

    public String getBelongCompany() {
        return belongCompany;
    }

    public void setBelongCompany(String belongCompany) {
        this.belongCompany = belongCompany;
    }

    public String getBelongDept() {
        return belongDept;
    }

    public void setBelongDept(String belongDept) {
        this.belongDept = belongDept;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
