package com.Robin.RobinServer.Entity;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

public class Course {
    @ExcelProperty(value = "课程编号")
    private String courseCode;

    @ExcelProperty(value = "课程名称")
    private String courseName;

    @ExcelProperty(value = "课程简介")
    private String courseIntroduction;

    @ExcelIgnore
    private String imgUrl;

    @ExcelIgnore
    private String courseMediaUrl;

    @ExcelIgnore
    private String courseCompare;

    @ExcelIgnore
    private String author;

    @ExcelIgnore
    private String belongCompany;

    public Course() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseIntroduction() {
        return courseIntroduction;
    }

    public void setCourseIntroduction(String courseIntroduction) {
        this.courseIntroduction = courseIntroduction;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCourseMediaUrl() {
        return courseMediaUrl;
    }

    public void setCourseMediaUrl(String courseMediaUrl) {
        this.courseMediaUrl = courseMediaUrl;
    }

    public String getCourseCompare() {
        return courseCompare;
    }

    public void setCourseCompare(String courseCompare) {
        this.courseCompare = courseCompare;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBelongCompany() {
        return belongCompany;
    }

    public void setBelongCompany(String belongCompany) {
        this.belongCompany = belongCompany;
    }
}
