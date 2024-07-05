package com.Robin.RobinServer.Entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@TableName(value = "robin_companyuser")
public class CompanyUser extends User{
    @TableId (value = "userName")
    private String userName;
    @TableField (value = "userRealName")
    private String userRealName; //真实姓名
    @TableField (value = "userPassword")
    private String userPassword;
    @TableField (value = "userPhoneNumber")
    private String userPhoneNumber;
    @TableField (value = "userEmail")
    private String userEmail;
    @TableField (value = "imgUrl")
    private String imgUrl; //头像图片url
    @TableField (value = "userType")
    private int userType; // 0：企业普通用户,  1：企业管理员,  2：超级管理员
    @TableField (value = "belongCompany")
    private String belongCompany;
    @TableField (value = "belongDept")
    private String belongDept;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField (value = "startTime",fill= FieldFill.INSERT)
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
