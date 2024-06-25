package com.Robin.RobinServer.Entity;

//会议管理部分，会议实体
public class Conference {
    private int conferenceId;
    private String conferenceName;
    private String creator;
    private String state; //会议状态：进行中 / 已结束
    private String content;
    private String beginTime;
    private String endTime;
    private String imgUrl; //会议封面url

    public Conference() {}

    public Conference(int conferenceId, String conferenceName, String creator, String state, String content, String beginTime, String endTime, String imgUrl) {
        this.conferenceId = conferenceId;
        this.conferenceName = conferenceName;
        this.creator = creator;
        this.state = state;
        this.content = content;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "conferenceId=" + conferenceId +
                ", conferenceName='" + conferenceName + '\'' +
                ", creator='" + creator + '\'' +
                ", state='" + state + '\'' +
                ", content='" + content + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
