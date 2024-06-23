package com.Robin.RobinServer.ViewEntity;

import com.Robin.RobinServer.Entity.CompanyUser;
import com.Robin.RobinServer.Util.DateUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CompanyUser_View { //隐藏了密码属性
    private String userName; //昵称
    private String userRealName; //真实姓名
    private String userPhoneNumber;
    private String userEmail;
    private String imgUrl;
    private int userType; // 0：普通用户,  1：企业管理员,  2：超级管理员
    private String belongCompany;
    private String belongDept;
    private String startTime;

    public CompanyUser_View(CompanyUser cu) {
        // 这儿难道不应该把需要的属性复制完吗？
        this.userName = cu.getUserName();
        this.userRealName = cu.getUserRealName();
        this.userPhoneNumber = cu.getUserPhoneNumber();
        this.userEmail = cu.getUserEmail();
        this.imgUrl = cu.getImgUrl();
        this.userType = cu.getUserType();
        this.belongCompany = cu.getBelongCompany();
        this.belongDept = cu.getBelongDept();
        this.startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cu.getStartTime());
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
