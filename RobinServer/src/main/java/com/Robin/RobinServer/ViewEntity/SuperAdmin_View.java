package com.Robin.RobinServer.ViewEntity;

import com.Robin.RobinServer.Entity.CompanyUser;
import com.Robin.RobinServer.Entity.SuperAdmin;
import com.Robin.RobinServer.Entity.User;

public class SuperAdmin_View { //隐藏了密码
    private String userName; //昵称
    private String userRealName; //真实姓名
    private String userPhoneNumber;
    private String userEmail;
    private String imgUrl;
    private int userType; // 0：普通用户,  1：企业管理员,  2：超级管理员

    public SuperAdmin_View(SuperAdmin admin) {
        this.userName = admin.getUserName();
        this.userRealName = admin.getUserRealName();
        this.userPhoneNumber = admin.getUserPhoneNumber();
        this.userEmail = admin.getUserEmail();
        this.imgUrl = admin.getImgUrl();
        this.userType = admin.getUserType();
    }

    //将多的信息进行提取即可
    public SuperAdmin_View(User user) {
        this.userName = user.getUserName();
        this.userRealName = user.getUserRealName();
        this.userPhoneNumber = user.getUserPhoneNumber();
        this.userEmail = user.getUserEmail();
        this.imgUrl = user.getImgUrl();
        this.userType = user.getUserType();
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
}
