package com.kazyonplus.CasesProcuration.service;

import com.kazyonplus.CasesProcuration.model.Case;
import com.kazyonplus.CasesProcuration.model.Session;
import com.kazyonplus.CasesProcuration.model.request.CaseRequest;
import com.kazyonplus.CasesProcuration.model.request.SessionRequest;
import com.kazyonplus.CasesProcuration.model.request.exception.SessionNotFoundException;
import com.kazyonplus.CasesProcuration.repository.CaseRepository;
import com.kazyonplus.CasesProcuration.repository.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepo sessionRepo;

    private final CaseRepository caseRepository;

    @Autowired
    public SessionService(SessionRepo sessionRepo, CaseRepository caseRepository)
    {
        this.sessionRepo = sessionRepo;
        this.caseRepository = caseRepository;
    }

    public CaseRequest addSession(SessionRequest sessionRequest, Long caseId){
        Case mycase = caseRepository.findById(caseId).get();
        List<Session> sessions = mycase.getSessions();
        sessions.add(Session.from(sessionRequest));
        caseRepository.save(mycase);
        return CaseRequest.from(mycase);
    }

    public List<Session> getSessionsByCaseId(Long caseId){
        return caseRepository.findById(caseId).get().getSessions();
    }

    public Session getSessionByID(Long id){
        return sessionRepo.findById(id).orElseThrow(() ->
                new SessionNotFoundException(id));
    }

    public Session deleteSessionByID(Long id){
        Session session = getSessionByID(id) ;
        sessionRepo.delete(session);
        return session;
    }

    @Transactional
    public Session editSesssionByID(Long id, Session session){
        Session sessionToEdit = getSessionByID(id) ;
        sessionToEdit.setDecisionStatus(session.getDecisionStatus());
        sessionToEdit.setStartingDate(session.getStartingDate());
        sessionToEdit.setEndingDate(session.getEndingDate());
        sessionRepo.save(sessionToEdit);
        return sessionToEdit ;
    }
}
