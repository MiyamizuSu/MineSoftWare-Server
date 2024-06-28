package com.Robin.RobinServer.Biz;


import com.Robin.RobinServer.Entity.Company;
import com.Robin.RobinServer.Mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CompanyBiz {
    @Autowired
    private CompanyMapper mapper;

    public void insertCompany(Company company) {
        boolean isDistinct = true;
//        List<String> allCompanyName=mapper.allCompanyName();
//        Iterator<String> iterator = allCompanyName.iterator();
//        while(iterator.hasNext()) {
//            String k=iterator.next();
//            if(k.equals(company.getCompanyName())) {
//                isDistinct = false;
//            }
//        }
        Company dbCompany = mapper.selectCompanyByName(company.getCompanyName());
        if (dbCompany != null && dbCompany.getCompanyName().equals(company.getCompanyName())) {
            isDistinct = false;
        }
        if(isDistinct) {
            mapper.insertCompany(company);
        }
       else {

        }
    }

    public List<Company> getAllCompanies() {
        return mapper.allCompanyList();
    }

    public List<String> getAllCompanyName() {
        return mapper.allCompanyName();
    }

    public Company getCompanyByName(String companyName) {
        return mapper.selectCompanyByName(companyName);
    }

}
