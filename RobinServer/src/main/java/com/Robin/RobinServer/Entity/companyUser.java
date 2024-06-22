package com.Robin.RobinServer.Entity;

import java.sql.Date;
import java.sql.Timestamp;

public class companyUser {
    private String userName;
    private String userPassword;
    private String belongCompany;
    private String userPhoneNumber;
    private String userEmail;
    private String belongDept;
    private String userRealName;
    private Timestamp startTime;
    private boolean isAdmin;

    public companyUser() {

    }

    public companyUser(String userName, String userPassword, String belongCompany, String userPhoneNumber, String userEmail, String userRealName, boolean isAdmin) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.belongCompany = belongCompany;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userRealName = userRealName;
        this.isAdmin = isAdmin;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
    public companyUser(String userName, String userPassword, String belongCompany, String userPhoneNumber, String userRealName,Timestamp startTime , boolean isAdmin) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.belongCompany = belongCompany;
        this.userPhoneNumber = userPhoneNumber;
        this.userRealName = userRealName;
        this.isAdmin = isAdmin;
        this.startTime=startTime;
    }

    public companyUser(String userName, String userPassword, String belongCompany) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.belongCompany = belongCompany;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getBelongCompany() {
        return belongCompany;
    }

    public void setBelongCompany(String belongCompany) {
        this.belongCompany = belongCompany;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
