package com.kazyonplus.CasesProcuration.model.request;

import com.kazyonplus.CasesProcuration.model.Session;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SessionRequest {
    private LocalDate startingDate;
    private LocalDate endingDate;
    private String decisionStatus ;

    public static SessionRequest from(Session session){
        SessionRequest sessionRequest = new SessionRequest();

        sessionRequest.setStartingDate(session.getStartingDate());
        sessionRequest.setEndingDate(session.getEndingDate());
        sessionRequest.setDecisionStatus(session.getDecisionStatus());
        return sessionRequest;
    }
}