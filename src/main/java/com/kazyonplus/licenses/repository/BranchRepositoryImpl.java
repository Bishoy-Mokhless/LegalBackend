package com.kazyonplus.licenses.repository;

import com.kazyonplus.licenses.model.Branch.Branch;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.hasText;

@Repository
public class BranchRepositoryImpl implements CustomBranchRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Branch> listByFilter(Map<String, String> filterMap) {
        StringBuilder queryBuilder = new StringBuilder("select b from Branch b");
        queryBuilder.append(createFilterQuery(filterMap));
        return entityManager.createQuery(queryBuilder.toString()).getResultList();
    }
    private String createFilterQuery(Map<String, String> filterMap){
        StringBuilder queryBuilder = new StringBuilder(" where 1=1 ");
        String governorate = filterMap.get("governorate");
        String province = filterMap.get("province");
        String status = filterMap.get("status");

        if(hasText(governorate)){
            queryBuilder.append("and b.governorate = '" + governorate +"' ");
        }
        if(hasText(province)){
            queryBuilder.append("and b.province = '" + province +"' ");
        }
        if(hasText(status)){
            queryBuilder.append("and b.status = '" + status +"' ");
        }
        return queryBuilder.toString();
    }
}
