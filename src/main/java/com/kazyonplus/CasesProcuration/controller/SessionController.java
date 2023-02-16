package com.kazyonplus.CasesProcuration.controller;

import com.kazyonplus.CasesProcuration.model.Session;
import com.kazyonplus.CasesProcuration.model.request.CaseRequest;
import com.kazyonplus.CasesProcuration.model.request.SessionRequest;
import com.kazyonplus.CasesProcuration.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@CrossOrigin(origins ={"http://localhost:4200/", "https://legalsystem.netlify.app/"} ,
        methods = {RequestMethod.GET,RequestMethod.DELETE,
                RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS,
                RequestMethod.POST},
        allowedHeaders = {"*"})
@RequestMapping("/session")
public class SessionController {
    private final SessionService sessionService ;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping(value = "/add/{caseId}")
    public ResponseEntity<CaseRequest> AddSessionToCase(
            @PathVariable final Long caseId,
            @RequestBody SessionRequest sessionRequest){
        return status(HttpStatus.CREATED).body(sessionService.addSession(sessionRequest, caseId));
    }

    @GetMapping(value = "/{caseId}")
    public ResponseEntity<List<Session>> getSessionsByCaseId(
            @PathVariable(name = "caseId") Long caseId){
        List<Session> sessions = sessionService.getSessionsByCaseId(caseId) ;
        return new ResponseEntity<>(sessions, HttpStatus.OK) ;
    }

    @PostMapping(value = "/edit/{id}")
    public ResponseEntity<SessionRequest> editSessionById(@PathVariable  Long id,
                                                          @RequestBody  SessionRequest sessionRequest) {
        Session editSession = sessionService.editSesssionByID(id, Session.from(sessionRequest));
        return new ResponseEntity<>(SessionRequest.from(editSession), HttpStatus.OK);
    }
//    @GetMapping(value = "{id}")
//    public ResponseEntity<SessionRequest> getSessionById(@PathVariable final Long id){
//        Session session = sessionService.getSessionByID(id) ;
//        return new ResponseEntity<>(SessionRequest.from(session), HttpStatus.OK) ;
//    }
//
//    @DeleteMapping(value = "{id}")
//    public ResponseEntity<SessionRequest> deleteSessionById(@PathVariable final Long id){
//        Session session = sessionService.deleteSessionByID(id) ;
//        return new ResponseEntity<>(SessionRequest.from(session), HttpStatus.OK) ;
//    }
//
}
