package com.Robin.RobinServer.Entity;

public class SuperAdmin extends User{

    public SuperAdmin() {
        super();
        this.setUserType(2);
    }

    public SuperAdmin(String userName, String userRealName, String userPassword, String userPhoneNumber, String userEmail, String imgUrl) {
        super(userName, userRealName, userPassword, userPhoneNumber, userEmail, imgUrl, 2);
    }

    public SuperAdmin(String userName, String userRealName, String userPassword, String userPhoneNumber) {
        super(userName, userRealName, userPassword, userPhoneNumber, 2);
    }
}
