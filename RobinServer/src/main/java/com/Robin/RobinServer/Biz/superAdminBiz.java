package com.Robin.RobinServer.Biz;


import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.Robin.RobinServer.Entity.superAdmin;
import com.Robin.RobinServer.Mapper.superAdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class superAdminBiz {
    @Autowired
    private com.Robin.RobinServer.Mapper.superAdminMapper mapper;
    private final Digester SHA256 = new Digester(DigestAlgorithm.SHA256);

    public int superAdminLogin(superAdmin superAdmin) {

        boolean isPassed=false;
        List<superAdmin> superAdmins=mapper.selectSuperAdmin();
        Iterator<superAdmin> iterator=superAdmins.iterator();
        while(iterator.hasNext()){
            superAdmin k=iterator.next();
            if(k.getAdminName().equals(superAdmin.getAdminName())&&k.getAdminPassword().equals(SHA256.digestHex(superAdmin.getAdminPassword()))){
                isPassed=true;
            }
        }
        if(isPassed){
            return 200;
        }
        else {
            return 302;
        }
    }

}
