package com.kazyonplus.CasesProcuration.model;

import com.kazyonplus.CasesProcuration.model.request.SessionRequest;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "sessions")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startingDate;
    private LocalDate endingDate;
    private String decisionStatus;


    public static Session from(SessionRequest sessionRequest){
        Session session = new Session();

        session.setStartingDate(sessionRequest.getStartingDate());
        session.setEndingDate(sessionRequest.getEndingDate());
        session.setDecisionStatus(sessionRequest.getDecisionStatus());
        return session;
    }
}