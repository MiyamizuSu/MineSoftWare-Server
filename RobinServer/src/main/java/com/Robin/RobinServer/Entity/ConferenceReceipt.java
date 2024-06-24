package com.Robin.RobinServer.Entity;

//参会回执，用于小程序端
public class ConferenceReceipt {
    private int receiptId; //数据库自动生成，递增
    private String companyName;
    private String userName;
    private String userGender;
    private String phoneNumber;
    private String userEmail;
    private String roomType;
    private String arrive; //到达方式
    private String arriveDetail;
    private String leave; //离开方式
    private String leaveDetail;
    private String DTW; //备注

    public ConferenceReceipt() {}

    public ConferenceReceipt(int receiptId, String companyName, String userName, String userGender, String phoneNumber, String userEmail, String roomType, String arrive, String arriveDetail, String leave, String leaveDetail, String DTW) {
        this.receiptId = receiptId;
        this.companyName = companyName;
        this.userName = userName;
        this.userGender = userGender;
        this.phoneNumber = phoneNumber;
        this.userEmail = userEmail;
        this.roomType = roomType;
        this.arrive = arrive;
        this.arriveDetail = arriveDetail;
        this.leave = leave;
        this.leaveDetail = leaveDetail;
        this.DTW = DTW;
    }

    @Override
    public String toString() {
        return "ConferenceReceipt{" +
                "receiptId=" + receiptId +
                ", companyName='" + companyName + '\'' +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", roomType='" + roomType + '\'' +
                ", arrive='" + arrive + '\'' +
                ", arriveDetail='" + arriveDetail + '\'' +
                ", leave='" + leave + '\'' +
                ", leaveDetail='" + leaveDetail + '\'' +
                ", DTW='" + DTW + '\'' +
                '}';
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getArriveDetail() {
        return arriveDetail;
    }

    public void setArriveDetail(String arriveDetail) {
        this.arriveDetail = arriveDetail;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }

    public String getLeaveDetail() {
        return leaveDetail;
    }

    public void setLeaveDetail(String leaveDetail) {
        this.leaveDetail = leaveDetail;
    }

    public String getDTW() {
        return DTW;
    }

    public void setDTW(String DTW) {
        this.DTW = DTW;
    }
}
