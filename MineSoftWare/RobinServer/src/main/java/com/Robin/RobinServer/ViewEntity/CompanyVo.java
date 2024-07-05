package com.Robin.RobinServer.ViewEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyVo{
    private String companyName;
    private String connectorName;
    private String imgUrl;
    private String userName;
    private String userPhoneNumber;
}
