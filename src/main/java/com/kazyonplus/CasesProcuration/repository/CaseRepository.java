package com.kazyonplus.CasesProcuration.repository;

import com.kazyonplus.CasesProcuration.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CaseRepository extends JpaRepository<Case,Long> {


    @Query("select c from Case c where c.client LIKE CONCAT(:name,'%')")
    List<Case> findByName(String name);

}
