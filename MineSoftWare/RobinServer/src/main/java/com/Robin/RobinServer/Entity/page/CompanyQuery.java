package com.Robin.RobinServer.Entity.page;

import lombok.Data;

@Data
public class CompanyQuery extends PageBean {
    private String currentUser;
    private String companyName;
    private String connectorName;
    private String userName;
    private String userPhoneNumber;
}
