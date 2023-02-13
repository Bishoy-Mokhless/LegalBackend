package com.kazyonplus.contracts.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kazyonplus.contracts.model.Contract;

import static java.lang.String.format;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class ContractRepositoryImpl implements CustomContractRepository{

    @PersistenceContext
    EntityManager entityManager;
    @Override

    public List<Contract> listByFilter(Map<String, String> filterMap) {
        StringBuilder queryBuilder = new StringBuilder("select c from Contract c");
        queryBuilder.append(createFilterQuery(filterMap));
        return entityManager.createQuery(queryBuilder.toString()).getResultList();
    }
//    @Override
    public Long countByStatus(String status){
        StringBuilder queryBuilder = new StringBuilder("select count(*) from Contract c ");
        queryBuilder.append("where c.status = '" + status +"'");
        return (Long) entityManager.createQuery(queryBuilder.toString()).getSingleResult();
    }
    private String createFilterQuery(Map<String, String> filterMap){
        StringBuilder queryBuilder = new StringBuilder(" where 1=1 ");
        String storeCode = filterMap.get("store_code");
        String branchName = filterMap.get("branch_name");
        String governorate = filterMap.get("governorate");
        String province = filterMap.get("province");
        String status = filterMap.get("status");
        String department = filterMap.get("department");
        String type = filterMap.get("type");

        if(hasText(storeCode)){
            queryBuilder.append(format("and c.storeCode = (%s) ", storeCode));
        }
        if(hasText(branchName)){
            queryBuilder.append("and c.branchName =  '" + branchName +"' ");
        }
        if(hasText(governorate)){
            queryBuilder.append("and c.governorate = '" + governorate +"' ");
        }
        if(hasText(province)){
            queryBuilder.append("and c.province = '" + province +"' ");
        }
        if(hasText(status)){
            queryBuilder.append("and c.status = '" + status +"' ");
        }
        if(hasText(department)){
            queryBuilder.append("and c.department = '" + department +"' ");
        }
        if(hasText(type)){
            queryBuilder.append("and c.type = '" + type +"' ");
        }
        return queryBuilder.toString();
    }
}
