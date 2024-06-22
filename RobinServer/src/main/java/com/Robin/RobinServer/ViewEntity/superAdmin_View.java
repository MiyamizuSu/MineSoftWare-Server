package com.Robin.RobinServer.ViewEntity;

import com.Robin.RobinServer.Entity.superAdmin;

public class superAdmin_View {
    private String superAdminName;

    public superAdmin_View(superAdmin superAdmin) {
        this.superAdminName=superAdmin.getAdminName();
    }
}
