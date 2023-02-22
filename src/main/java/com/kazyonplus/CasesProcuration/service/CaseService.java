package com.kazyonplus.CasesProcuration.service;

import com.kazyonplus.CasesProcuration.model.Case;
import com.kazyonplus.CasesProcuration.model.Session;
import com.kazyonplus.CasesProcuration.model.request.exception.CaseNotFoundException;
import com.kazyonplus.CasesProcuration.repository.CaseRepository;
import com.kazyonplus.contracts.model.Contract;
import com.kazyonplus.contracts.model.ContractResponse;
import com.kazyonplus.files.controller.DocController;
import com.kazyonplus.files.model.Doc;
import com.kazyonplus.files.repository.DocRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//@CrossOrigin(origins = "http://localhost:4200/")
@Service
public class CaseService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private SessionService sessionService;
    @Autowired
    DocController docController;
    @Autowired
    DocRepository docRepository;
    public Case addCase(Case mycase){
        return caseRepository.save(mycase);
    }

    public List<Case> getCases(){
        return StreamSupport
                .stream(caseRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public Case getCaseById(Long id){
        return caseRepository.findById(id).orElseThrow(() ->
                new CaseNotFoundException(id));
    }

    public Case deleteCaseByID(Long id){
        Case mycase = getCaseById(id);
        caseRepository.delete(mycase);
        return mycase;
    }

    @Transactional
    public Case editCase(Long id, Case mycase){
        Case CaseToEdit = getCaseById(id);

        CaseToEdit.setCaseyear(mycase.getCaseyear());
        CaseToEdit.setFilenumber(mycase.getFilenumber());
        CaseToEdit.setNumbercase(mycase.getNumbercase());
        CaseToEdit.setArea(mycase.getArea());
        CaseToEdit.setClientstat(mycase.getClientstat());
        CaseToEdit.setClient(mycase.getClient());
        CaseToEdit.setAgainststat(mycase.getAgainststat());
        CaseToEdit.setAgainst(mycase.getAgainst());
        CaseToEdit.setCategory(mycase.getCategory());

        return CaseToEdit;
    }

    @Transactional
    public Case addSessionToCase(Long CaseId, Long SessionId){
        Case mycase = getCaseById(CaseId);
        Session session = sessionService.getSessionByID(SessionId) ;
        mycase.addSession(session);
        return mycase;
    }

    @Transactional
    public Case removeSessionFromCase(Long CaseId, Long SessionId){
        Case mycase = getCaseById(CaseId);
        Session mysession = sessionService.getSessionByID(SessionId) ;
        mycase.removeSession(mysession);
        return mycase;
    }

    public List<Case> getCaseByName(String name) {
        return caseRepository.findByName(name) ;
    }
    public String addFile(long caseId, MultipartFile file) throws IOException {
        String docname = file.getOriginalFilename();

        Doc doc = new Doc(docname,file.getContentType(),file.getBytes());
        docRepository.save(doc);

        Case _case = caseRepository.getById(caseId);

        _case.setAttachment(doc);
        _case.setHasAttachment(true);
        caseRepository.save(_case);
        return "done";
    }

}

