package com.kazyonplus.licenses.repository;

import com.kazyonplus.licenses.model.Branch.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BranchRepository extends JpaRepository<Branch, Long>, CustomBranchRepository {
    public List<Branch> findBranchesBySapCodeStartsWithOrStoreCodeStartsWith(String sapCode, String storeCode);
}
