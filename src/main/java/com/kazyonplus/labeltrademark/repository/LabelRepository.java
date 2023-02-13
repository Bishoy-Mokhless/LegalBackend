package com.kazyonplus.labeltrademark.repository;

import com.kazyonplus.labeltrademark.model.Label.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface LabelRepository extends JpaRepository<Label, Long> {
    public List<Label> findAllByCodeStartsWithOrNameStartsWith(String searchTerm1, String searchTerm2);

    @Query("select code from Label")
    public List<String> listCodes();
    public List<Label> findAllByExpiryDateBetween(Date date1, Date date2);
}
