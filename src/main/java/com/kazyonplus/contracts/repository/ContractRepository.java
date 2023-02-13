package com.kazyonplus.contracts.repository;

import java.util.List;

import com.kazyonplus.contracts.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ContractRepository extends JpaRepository<Contract, Long>, CustomContractRepository {
    @Query("select storeCode from Contract")
    List<Integer> listStoreCodes();
    Contract getContractByStoreCode(Long storeCode);
}
