package com.kazyonplus.CasesProcuration.repository;

import com.kazyonplus.CasesProcuration.model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SessionRepo extends CrudRepository<Session,Long> {
}
