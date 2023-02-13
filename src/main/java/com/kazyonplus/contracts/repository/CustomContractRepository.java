package com.kazyonplus.contracts.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kazyonplus.contracts.model.Contract;

@Repository
public interface CustomContractRepository  {
    public List<Contract> listByFilter(Map<String, String> filterMap);

    Long countByStatus(String status);
}
