package com.kazyonplus.CasesProcuration.model.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kazyonplus.CasesProcuration.model.Case;
import lombok.Data;

import java.util.Date;

@Data
public class CasePlainRequest {
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
    private boolean hasAttachment;

    public static CasePlainRequest from(Case Mycase){
        CasePlainRequest casePlainRequest = new CasePlainRequest();

        casePlainRequest.setIdCase(Mycase.getIdCase());
        casePlainRequest.setAgainst(Mycase.getAgainst());
        casePlainRequest.setArea(Mycase.getArea());
        casePlainRequest.setCaseyear(Mycase.getCaseyear());
        casePlainRequest.setClient(Mycase.getClient());
        casePlainRequest.setAgainststat(Mycase.getAgainststat());
        casePlainRequest.setClientstat(Mycase.getClientstat());
        casePlainRequest.setFilenumber(Mycase.getFilenumber());
        casePlainRequest.setNumbercase(Mycase.getNumbercase());
        casePlainRequest.setCategory(Mycase.getCategory());

        return casePlainRequest;
    }
}