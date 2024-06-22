package com.Robin.RobinServer.Mapper;

import com.Robin.RobinServer.Entity.superAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface superAdminMapper {
    @Select("SELECT * from robin_superadmin ")
    List<superAdmin> selectSuperAdmin();


}
