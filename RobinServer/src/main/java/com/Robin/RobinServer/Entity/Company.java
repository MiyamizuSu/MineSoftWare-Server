package com.Robin.RobinServer.Entity;

public class Company {
    private String companyName;
    private String connectorName;
    private String imgUrl; //企业图标url

    public Company() {}

    public Company(String companyName, String connectorName, String imgUrl) {
        this.companyName = companyName;
        this.connectorName = connectorName;
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", connectorName='" + connectorName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getConnectorName() {
        return connectorName;
    }

    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
