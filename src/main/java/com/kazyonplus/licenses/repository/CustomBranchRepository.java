package com.kazyonplus.licenses.repository;

import com.kazyonplus.licenses.model.Branch.Branch;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomBranchRepository {
    public List<Branch> listByFilter(Map<String, String> filterMap);
}
