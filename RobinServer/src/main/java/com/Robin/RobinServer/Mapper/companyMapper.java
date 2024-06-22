package com.Robin.RobinServer.Mapper;


import com.Robin.RobinServer.Entity.company;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface companyMapper {

    @Insert("INSERT INTO robin_company (companyName,connectorName) values (#{companyName},#{connectorName})")
    public void insertCompany(company company);

    @Select("SELECT companyName from robin_company")
    public List<String> allCompanyName();

}
