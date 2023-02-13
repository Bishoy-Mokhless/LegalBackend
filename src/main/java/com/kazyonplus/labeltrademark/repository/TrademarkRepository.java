package com.kazyonplus.labeltrademark.repository;

import com.kazyonplus.labeltrademark.model.Trademark.Trademark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface TrademarkRepository extends JpaRepository<Trademark, Long> {
    @Query("select code from Trademark")
    public List<String> listCodes();
    public List<Trademark> findAllByCodeStartsWithOrNameStartsWith(String searchTerm1, String searchTerm2);
    public List<Trademark> findAllByExpiryDateBetween(Date date1, Date date2);
}
