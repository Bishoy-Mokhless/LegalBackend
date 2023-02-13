package com.kazyonplus.CasesProcuration.repository;

import com.kazyonplus.CasesProcuration.model.Procuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcurationRepository extends JpaRepository<Procuration, Long> {
    @Query("SELECT p from Procuration p WHERE p.client_name LIKE CONCAT(:name,'%') ")
    List<Procuration> searchProcurationsByName(String name);
}
