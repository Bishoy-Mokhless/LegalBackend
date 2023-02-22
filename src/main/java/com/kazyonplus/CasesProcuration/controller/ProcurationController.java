package com.kazyonplus.CasesProcuration.controller;

import com.kazyonplus.CasesProcuration.model.Case;
import com.kazyonplus.CasesProcuration.model.Procuration;
import com.kazyonplus.CasesProcuration.model.ProcurationDTO;

import com.kazyonplus.CasesProcuration.repository.ProcurationRepository;
import com.kazyonplus.CasesProcuration.service.ProcurationService;
//import com.kazyonplus.config.SecurityConfig;
import com.kazyonplus.files.model.Doc;
import com.kazyonplus.files.repository.DocRepository;
import com.kazyonplus.utils.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    DocRepository docRepository;
    @Autowired
    private ProcurationRepository procurationRepository;

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
    @PostMapping("/addFile")
    public ResponseEntity<String> updateContractFile(/*@PathVariable("id") long id,*/ @RequestParam("files") MultipartFile files ,@RequestParam("id") long id) throws IOException {

        return status(OK).body(procurationService.addFile(id,files));
    }
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable ("id") int id) {
        Procuration procuration =procurationRepository.getById((long) id);
        int idd = procuration.getAttachment().getId();
        Doc doc = docRepository.getById(idd);
//get file from repo
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+doc.getDocName()+"\"")
                .body(new ByteArrayResource(doc.getData()));
    }
}