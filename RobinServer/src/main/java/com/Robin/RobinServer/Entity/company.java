package com.Robin.RobinServer.Entity;

public class company {
    private String companyName;
    private String connectorName;

    public company() {
    }

    public company(String companyName, String connectorName) {
        this.companyName = companyName;
        this.connectorName = connectorName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getConnectorName() {
        return connectorName;
    }

    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }
}
