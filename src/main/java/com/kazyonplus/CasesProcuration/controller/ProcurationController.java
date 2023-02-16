package com.kazyonplus.CasesProcuration.controller;

import com.kazyonplus.CasesProcuration.model.Procuration;
import com.kazyonplus.CasesProcuration.model.ProcurationDTO;

import com.kazyonplus.CasesProcuration.service.ProcurationService;
//import com.kazyonplus.config.SecurityConfig;
import com.kazyonplus.utils.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.List;


import static org.springframework.http.HttpStatus.*;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin(origins ={"http://localhost:4200/", "https://legalsystem.netlify.app/"} ,
        methods = {RequestMethod.GET,RequestMethod.DELETE,
                RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS,
                RequestMethod.POST},
        allowedHeaders = {"*"})
@RequestMapping("/procuration")
public class ProcurationController {
    @Autowired
    ProcurationService procurationService;
    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/add")
    public ResponseEntity<ProcurationDTO> addProcuration(@RequestBody Procuration proc){
        ProcurationDTO procurationDTO= procurationService.addProcuration(proc);
        return status(CREATED).body(procurationDTO);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<ProcurationDTO> findById(@PathVariable Long id){
        return ok(procurationService.findByID(id));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<ProcurationDTO>> getAllProcurations(){
        ResponseEntity<List<ProcurationDTO>> response = ok(procurationService.getAllProcurations());
        //addCORSHeaders((HttpServletResponse) response);

        return response;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProcurationDTO>>searchProcurationsByName(@RequestParam("name") String name){
        return ok(procurationService.searchProcurationsByName(name));
    }
    //test
    @PostMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateProcuration( @PathVariable("id") long id,@RequestBody ProcurationDTO dto){
        procurationService.updateProcuration(dto, id);
        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProcuration(@PathVariable Long id){
        procurationService.deleteProcuration(id);
        return new ResponseEntity<>(OK);
    }
}