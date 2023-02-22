package com.kazyonplus.files.repository;


import com.kazyonplus.files.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocRepository  extends JpaRepository<Doc,Integer>{

    //Optional<Doc> findByCategoryAndCategoryId(String category, long categoryId);
}
