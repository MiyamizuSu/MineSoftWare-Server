package com.Robin.RobinServer.Entity.page;

import com.Robin.RobinServer.Entity.page.PageBean;
import lombok.Data;

@Data
public class UserQuery extends PageBean {
    private String currentUser;
    private String userName;
    private String userRealName;
    private String userPhoneNumber;
    private String belongCompany;

}
