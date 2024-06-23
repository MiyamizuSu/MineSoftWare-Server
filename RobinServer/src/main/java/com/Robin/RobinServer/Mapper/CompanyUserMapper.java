package com.Robin.RobinServer.Mapper;


import com.Robin.RobinServer.Entity.CompanyUser;
import com.Robin.RobinServer.ViewEntity.CompanyUser_View;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyUserMapper {
    @Insert("INSERT INTO robin_companyuser values (#{userName},#{userRealName},#{userPassword},#{userPhoneNumber},#{userEmail}," +
            "#{imgUrl},#{userType},#{belongCompany},#{belongDept},#{startTime})")
    int insertCompanyUser(CompanyUser companyUser);

    @Select("SELECT * from robin_companyuser")
    List<CompanyUser> allUsers();

    @Select("SELECT * from robin_companyuser where userName=#{userName}")
    CompanyUser selectUserByName(String userName);

    //根据公司名称选出所有属于该公司的用户
    @Select("select * from robin_companyuser where belongCompany=#{companyName}")
    List<CompanyUser> selectUsersByCompany(String companyName);

    @Delete("delete from robin_companyuser where userName=#{userName}")
    int delByName(String userName);

    //更改除密码以外的可更改信息
//    @Update("update robin_companyuser set userName=#{e.userName},userPhoneNumber=#{e.userPhoneNumber},userEmail=#{e.userEmail},imgUrl=#{e.imgUrl},belongDept=#{e.belongDept},userType=#{e.userType} where userName=#{userName_before};" +
//            "update robin_company set connectorName=#{e.userName} where connectorName=#{userName_before};") //修改主键时需要修改相关的外键
//    public int updateCompanyUser(@Param("e") CompanyUser_View companyUser_view, @Param("userName_before") String userName_before);

    // MySQL不允许在单个 @Update 注解中执行多个更新操作，所以要将上面的方法拆成两个单独的update，并在biz层让它们同时执行
    // 更新robin_companyuser表
    @Update("update robin_companyuser set userName=#{e.userName}, userPhoneNumber=#{e.userPhoneNumber}, userEmail=#{e.userEmail}, imgUrl=#{e.imgUrl}, belongDept=#{e.belongDept}, userType=#{e.userType} where userName=#{userName_before}")
    public int updateCompanyUser(@Param("e") CompanyUser_View companyUser_view, @Param("userName_before") String userName_before);

    // 更新robin_company表
    @Update("update robin_company set connectorName=#{e.userName} where connectorName=#{userName_before}")
    public int updateCompany(@Param("e") CompanyUser_View companyUser_view, @Param("userName_before") String userName_before);


    @Update("update robin_companyuser set userPassword=#{newPassword} where userName=#{userName}")
    public int updatePassword(@Param("userName")String userName, @Param("newPassword")String newPassword);

}
