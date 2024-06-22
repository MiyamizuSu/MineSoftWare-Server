package com.Robin.RobinServer.Biz;


import com.Robin.RobinServer.Entity.company;
import com.Robin.RobinServer.Mapper.companyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class companyBiz {
    @Autowired
    private companyMapper mapper;

    public void insertCompany(company company) {
        boolean isDistinct = true;
        List<String> allCompanyName=mapper.allCompanyName();
        Iterator<String> iterator = allCompanyName.iterator();
        while(iterator.hasNext()) {
            String k=iterator.next();
            if(k.equals(company.getCompanyName())) {
                isDistinct = false;
            }
        }
        if(isDistinct) {
            mapper.insertCompany(company);
        }
       else {

        }
    }

    public List<String> getAllCompanyName() {
        return mapper.allCompanyName();
    }
}
