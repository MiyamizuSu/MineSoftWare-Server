package com.Robin.RobinServer.Mapper;


import com.Robin.RobinServer.Entity.companyUser;
import com.Robin.RobinServer.ViewEntity.companyUser_View;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface companyUserMapper {
    @Insert("INSERT INTO robin_companyuser(userName,userPassword,belongCompany,userPhoneNumber,userRealName,isAdmin,startTime) values (#{userName},#{userPassword},#{belongCompany},#{userPhoneNumber},#{userRealName},#{isAdmin},#{startTime})")
    public void insertCompanyUser(companyUser companyUser);

    @Select("SELECT * from robin_companyuser")
    public List<companyUser> allUser();

    @Select("SELECT * from robin_companyuser where userName=#{userName}")
    public companyUser findUserByName(String userName);

    @Update("update robin_companyuser set userName=#{userName},userEmail=#{userEmail},userPhoneNumber=#{userPhoneNumber} where userName=#{userName} ")
    public void updateCompanyUser(companyUser_View companyUser_view);

    @Update("update robin_companyuser set userPassword=#{newPassword} where userName=#{userName}")
    public void updatePassword(@Param("userName")String userName, @Param("newPassword") String newPassword);

}
