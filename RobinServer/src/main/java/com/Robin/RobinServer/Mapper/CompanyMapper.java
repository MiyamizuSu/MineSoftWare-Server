package com.Robin.RobinServer.Mapper;


import com.Robin.RobinServer.Entity.Company;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Insert("INSERT INTO robin_company values (#{companyName}, #{connectorName}, #{imgUrl})")
    public int insertCompany(Company company);

    @Select("SELECT * from robin_company")
    List<Company> allCompanyList();

    @Select("SELECT companyName from robin_company")
    List<String> allCompanyName();

    @Select("select * from robin_company where companyName=#{companyName}")
    Company selectCompanyByName(String companyName);
}
