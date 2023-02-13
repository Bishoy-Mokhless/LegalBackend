package com.kazyonplus.CasesProcuration.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kazyonplus.CasesProcuration.model.Case;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CaseRequest {
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
    private List<SessionRequest> sessionRequests;
    private boolean hasAttachment;

    public static CaseRequest from(Case Mycase){

        CaseRequest caseDto = new CaseRequest();

        caseDto.setIdCase(Mycase.getIdCase());
        caseDto.setClient(Mycase.getClient());
        caseDto.setAgainst(Mycase.getAgainst());
        caseDto.setArea(Mycase.getArea());
        caseDto.setCaseyear(Mycase.getCaseyear());
        caseDto.setClient(Mycase.getClient());
        caseDto.setAgainststat(Mycase.getAgainststat());
        caseDto.setClientstat(Mycase.getClientstat());
        caseDto.setFilenumber(Mycase.getFilenumber());
        caseDto.setNumbercase(Mycase.getNumbercase());
        caseDto.setCategory(Mycase.getCategory());
        caseDto.setHasAttachment(Mycase.isHasAttachment());
        caseDto.setSessionRequests(Mycase.getSessions().stream().map(SessionRequest::from).collect(Collectors.toList()) );

        return caseDto;
    }

}
