package com.kazyonplus.CasesProcuration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kazyonplus.CasesProcuration.model.request.CaseRequest;
import com.kazyonplus.CasesProcuration.model.request.SessionRequest;

import com.kazyonplus.files.model.Doc;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "cases")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdCase ;
    private Integer numbercase;
    private String client;
    private String against;
    private Integer filenumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date caseyear;
    private String category ;
    private String clientstat ;
    private String againststat ;
    private String area ;
    private String URl ;
    private String name ;
    private boolean hasAttachment = false;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "case_id")
    private List<Session> Sessions;
    @OneToOne
    private Doc attachment;


    public void addSession(Session session){
        Sessions.add(session);
    }

    public void removeSession(Session session){
        Sessions.remove(session) ;
    }

    public static Case from(CaseRequest caseDTO){
        Case mycase = new Case();

        mycase.setCaseyear(caseDTO.getCaseyear());
        mycase.setFilenumber(caseDTO.getFilenumber());
        mycase.setNumbercase(caseDTO.getNumbercase());
        mycase.setArea(caseDTO.getArea());
        mycase.setClientstat(caseDTO.getClientstat());
        mycase.setClient(caseDTO.getClient());
        mycase.setAgainststat(caseDTO.getAgainststat());
        mycase.setAgainst(caseDTO.getAgainst());
        mycase.setCategory(caseDTO.getCategory());
        mycase.setHasAttachment(caseDTO.isHasAttachment());
        List<Session> sessions = new LinkedList<>();
        for(SessionRequest sr: caseDTO.getSessionRequests()){
            sessions.add(Session.from(sr));
        }
        mycase.setSessions(sessions);
        return mycase;
    }

}
