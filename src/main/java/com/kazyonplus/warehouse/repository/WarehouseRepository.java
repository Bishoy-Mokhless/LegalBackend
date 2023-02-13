package com.kazyonplus.warehouse.repository;

import com.kazyonplus.warehouse.model.WarehouseLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface WarehouseRepository extends JpaRepository<WarehouseLicense, Long> {
    @Query("SELECT p from WarehouseLicense p WHERE p.status = :status ")
    List<WarehouseLicense> searchWarehouseByStatus(String status);

    @Query("SELECT p from WarehouseLicense p WHERE p.address LIKE CONCAT(:address,'%') ")
    List<WarehouseLicense> searchWarehouseByAddress(String address);


}
