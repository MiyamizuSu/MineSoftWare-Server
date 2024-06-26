package com.Robin.RobinServer.Entity;

public class User {
    private String userName; //昵称
    private String userRealName; //真实姓名
    private String userPassword;
    private String userPhoneNumber;
    private String userEmail;
    private String imgUrl; //头像图片url
    private int userType; // 0：企业普通用户,  1：企业管理员,  2：超级管理员

    public User() {}

    public User(String userName, String userRealName, String userPassword, String userPhoneNumber, String userEmail, String imgUrl, int userType) {
        this.userName = userName;
        this.userRealName = userRealName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.imgUrl = imgUrl;
        this.userType = userType;
    }

    public User(String userName, String userRealName, String userPassword, String userPhoneNumber, int userType) {
        this.userName = userName;
        this.userRealName = userRealName;
        this.userPassword = userPassword;
        this.userPhoneNumber = userPhoneNumber;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", UserType=" + userType +
                '}';
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
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
