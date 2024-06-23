package com.Robin.RobinServer.Mapper;

import com.Robin.RobinServer.Entity.SuperAdmin;
import com.Robin.RobinServer.ViewEntity.SuperAdmin_View;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SuperAdminMapper {
    @Select("SELECT * from robin_superadmin ")
    List<SuperAdmin> selectSuperAdmins();

    @Select("select * from robin_superadmin where userName=#{userName}")
    SuperAdmin selectAdminByName(String userName);

    @Insert("INSERT INTO robin_superadmin values (#{userName},#{userRealName},#{userPassword},#{userPhoneNumber}," +
            "#{userEmail},#{imgUrl},#{userType})")
    int insertAdmin(SuperAdmin admin);

    //更改除密码以外的可更改信息
    @Update("update robin_superadmin set userName=#{e.userName},userRealName=#{e.userRealName}," +
            "userPhoneNumber=#{e.userPhoneNumber},userEmail=#{e.userEmail},imgUrl=#{e.imgUrl} where userName=#{userName_before}")
    int updateAdmin(@Param("e") SuperAdmin_View adminView, @Param("userName_before") String userName_before);

    @Update("update robin_superadmin set userPassword=#{newPassword} where userName=#{userName}")
    public int updatePassword(@Param("userName")String userName, @Param("newPassword")String newPassword);
}
