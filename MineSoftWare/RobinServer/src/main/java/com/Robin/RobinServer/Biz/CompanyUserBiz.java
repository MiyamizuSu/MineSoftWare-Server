package com.Robin.RobinServer.Biz;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.Robin.RobinServer.Entity.CompanyUser;
import com.Robin.RobinServer.Mapper.CompanyUserMapper;
import com.Robin.RobinServer.ViewEntity.CompanyUser_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyUserBiz {

    private final Digester SHA256 = new Digester(DigestAlgorithm.SHA256);
    @Autowired
    private CompanyUserMapper mapper;

    public String insertNewCompanyUser(CompanyUser companyUser)  {
        try {
            boolean isDistinct = true;
//            List<CompanyUser> allUser = mapper.allUser();
//            Iterator<CompanyUser> iterator = allUser.iterator();
//            while (iterator.hasNext()) {
//                CompanyUser k = iterator.next();
//                if (k.getUserName().equals(companyUser.getUserName())) {
//                    isDistinct = false;
//                }
//            }
            // 为什么不直接用数据库的selectByName呢？
            CompanyUser dbUser = mapper.selectUserByName(companyUser.getUserName());
            if (dbUser != null && dbUser.getUserName().equals(companyUser.getUserName())) {
                isDistinct = false;
            }
            if (isDistinct) {
                companyUser.setUserPassword(SHA256.digestHex(companyUser.getUserPassword()));
                System.out.println("将要insert的CompanyUser："+companyUser);
                mapper.insertCompanyUser(companyUser);
                return "200";
            } else {
                return "301";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return "777";
        }

    }
    public CompanyUser companyUserLogin(String userName, String password, String companyName) {
//        boolean isPassed = false;
//        List<CompanyUser> allUser =mapper.allUser();
//        Iterator<CompanyUser> iterator = allUser.iterator();
//        while (iterator.hasNext()) {
//            CompanyUser k=iterator.next();
//            if (k.getUserName().equals(companyUser.getUserName())&&
//                    k.getUserPassword().equals(SHA256.digestHex(companyUser.getUserPassword()))&&
//                    k.getBelongCompany().equals(companyUser.getBelongCompany()))
//            {
//                isPassed=true;
//            }
//        }
//        if (isPassed) {
//            return 200;
//        }
//        else {
//            return 302;
//        }
        CompanyUser dbUser = mapper.selectUserByName(userName);
        if (dbUser != null && dbUser.getUserPassword().equals(SHA256.digestHex(password))
            && dbUser.getBelongCompany().equals(companyName)) {
            return dbUser; //登录有效才返回
        }
        return null;
    }
    public CompanyUser_View loadingData(CompanyUser_View companyUser_View) {
        CompanyUser e= mapper.selectUserByName(companyUser_View.getUserName());
//        System.out.println(e.getStartTime());
//        companyUser_View.setUserEmail(e.getUserEmail());
//        companyUser_View.setUserRealName(e.getUserRealName());
//        companyUser_View.setUserPhoneNumber(e.getUserPhoneNumber());
//        companyUser_View.setBelongDept(e.getBelongDept());
//        companyUser_View.setStartTime(DateUtil.fromTimeStampToString(e.getStartTime()));
//        companyUser_View.setStartTime(e.getStartTime());
//        return companyUser_View;
        return new CompanyUser_View(e);
    }

    public boolean removeUserByName(String userName) {
        return mapper.delByName(userName) > 0;
    }

    public String updateCompanyUser(CompanyUser_View companyUser_view, String userName_before) {
        try{
            String userName_new = companyUser_view.getUserName();
            if (userName_new.isEmpty()) {
                return "303"; //修改后的用户名称为空
            }
            CompanyUser dbUser = mapper.selectUserByName(userName_new);
            if (dbUser != null && !userName_new.equals(userName_before)) {
                return "301"; //修改后的用户名与其他用户名重复
            }
            mapper.updateCompanyUser(companyUser_view, userName_before);
            mapper.updateCompany(companyUser_view, userName_before);
            return "200";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "777";
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

    public List<CompanyUser> getAllUserList() {
        return mapper.allUsers();
    }

    public CompanyUser getCompanyUserByName(String userName) {
        return mapper.selectUserByName(userName);
    }

    public List<CompanyUser> getUsersByCompanyName(String companyName) {
        return mapper.selectUsersByCompany(companyName);
    }
    public String getUserCompany(String userName){
        return mapper.selectCompanyByUserName(userName);
    }
}
