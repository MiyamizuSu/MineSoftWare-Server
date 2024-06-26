package com.Robin.RobinServer.Biz;


import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.Robin.RobinServer.Entity.CompanyUser;
import com.Robin.RobinServer.Entity.SuperAdmin;
import com.Robin.RobinServer.Mapper.SuperAdminMapper;
import com.Robin.RobinServer.ViewEntity.CompanyUser_View;
import com.Robin.RobinServer.ViewEntity.SuperAdmin_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class SuperAdminBiz {
    @Autowired
    private SuperAdminMapper mapper;
    private final Digester SHA256 = new Digester(DigestAlgorithm.SHA256);

//    public int superAdminLogin(String userName, String password) {
//        boolean isPassed=false;
//        List<SuperAdmin> superAdmins=mapper.selectSuperAdmin();
//        Iterator<SuperAdmin> iterator=superAdmins.iterator();
//        while(iterator.hasNext()){
//            SuperAdmin k=iterator.next();
//            if(k.getAdminName().equals(superAdmin.getAdminName())&&k.getAdminPassword().equals(SHA256.digestHex(superAdmin.getAdminPassword()))){
//                isPassed=true;
//            }
//        }
//        if(isPassed){
//            return 200;
//        }
//        else {
//            return 302;
//        }
//    }

    public String insertNewAdmin(SuperAdmin admin) {
        try {
            boolean isDistinct = true;
            SuperAdmin dbAdmin = mapper.selectAdminByName(admin.getUserName());
            if (dbAdmin != null && dbAdmin.getUserName().equals(admin.getUserName())) {
                isDistinct = false;
            }
            if (isDistinct) {
                admin.setUserPassword(SHA256.digestHex(admin.getUserPassword()));
                mapper.insertAdmin(admin);
                return "200";
            } else {
                return "301";
            }
        }
        catch (Exception e) {
            return "301";
        }
    }

    public SuperAdmin superAdminLogin(String userName, String password) {
        SuperAdmin dbAdmin = mapper.selectAdminByName(userName);
        if (dbAdmin != null && dbAdmin.getUserPassword().equals(SHA256.digestHex(password))) {
            return dbAdmin; //登录有效才返回
        }
        return null;

    }

    public SuperAdmin_View loadingData(SuperAdmin_View adminView) {
        SuperAdmin admin = mapper.selectAdminByName(adminView.getUserName());
        return new SuperAdmin_View(admin);
    }

    public String updateAdmin(SuperAdmin_View adminView, String userName_before) {
        try{
            String userName_new = adminView.getUserName();
            if (userName_new.isEmpty()) {
                return "303"; //修改后的用户名称为空
            }
            SuperAdmin dbAdmin = mapper.selectAdminByName(userName_new);
            if (dbAdmin != null && !userName_new.equals(userName_before)) {
                return "301"; //修改后的用户名与其他用户名重复
            }
            mapper.updateAdmin(adminView, userName_before);
            return "200";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "301";
        }
    }
    public String updatePassword(String userName,String newPassword){
        try{
            if (newPassword.isEmpty()) {
                return "304"; //修改后的用户密码为空
            }

            mapper.updatePassword(userName,SHA256.digestHex(newPassword));
            return "200";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "777";
        }
    }

}
