package com.Robin.RobinServer.Biz;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.Robin.RobinServer.Entity.companyUser;
import com.Robin.RobinServer.Mapper.companyUserMapper;
import com.Robin.RobinServer.Mapper.superAdminMapper;
import com.Robin.RobinServer.Util.dateUtil;
import com.Robin.RobinServer.ViewEntity.companyUser_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
import java.util.List;

@Service
public class companyUserBiz {

    private final Digester SHA256 = new Digester(DigestAlgorithm.SHA256);
    @Autowired
    private companyUserMapper mapper;

    public int insertNewCompanyUser(companyUser companyUser)  {
        try {
            boolean isDistinct = true;
            List<companyUser> allUser = mapper.allUser();
            Iterator<companyUser> iterator = allUser.iterator();
            while (iterator.hasNext()) {
                companyUser k = iterator.next();
                if (k.getUserName().equals(companyUser.getUserName())) {
                    isDistinct = false;
                }
            }
            if (isDistinct) {
                companyUser.setUserPassword(SHA256.digestHex(companyUser.getUserPassword()));
                mapper.insertCompanyUser(companyUser);
                return 200;
            } else {
                return 301;
            }
        }
        catch (Exception e) {
            return 301;
        }

    }
    public int companyUserLogin(companyUser companyUser) {
        boolean isPassed = false;
        List<companyUser> allUser =mapper.allUser();
        Iterator<companyUser> iterator = allUser.iterator();
        while (iterator.hasNext()) {
            companyUser k=iterator.next();
            if (k.getUserName().equals(companyUser.getUserName())&&
                    k.getUserPassword().equals(SHA256.digestHex(companyUser.getUserPassword()))&&
                    k.getBelongCompany().equals(companyUser.getBelongCompany()))
            {
                isPassed=true;
            }
        }
        if (isPassed) {
            return 200;
        }
        else {
            return 302;
        }
    }
    public companyUser_View loadingData(companyUser_View companyUser_View) {
        companyUser e= mapper.findUserByName(companyUser_View.getUserName());
        System.out.println(e.getStartTime());
        companyUser_View.setUserEmail(e.getUserEmail());
        companyUser_View.setUserRealName(e.getUserRealName());
        companyUser_View.setUserPhoneNumber(e.getUserPhoneNumber());
        companyUser_View.setAdmin(e.isAdmin());
        companyUser_View.setBelongDept(e.getBelongDept());
        companyUser_View.setStartTime(dateUtil.fromTimeStampToString(e.getStartTime()));
        return companyUser_View;
    }
    public int updateCompanyUser(companyUser_View companyUser_view) {
        try{
            mapper.updateCompanyUser(companyUser_view);
            return 200;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 301;
        }
    }
    public int updatePassword(String userName,String newPassword){
        try{
            mapper.updatePassword(userName,SHA256.digestHex(newPassword));
            return 200;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 777;
        }
    }
}
