package com.kazyonplus.contracts.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.kazyonplus.contracts.model.*;
import com.kazyonplus.contracts.repository.ContractRepository;
import com.kazyonplus.contracts.service.ContractService;

import com.kazyonplus.files.controller.DocController;
import com.kazyonplus.files.model.Doc;
import com.kazyonplus.files.repository.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin(origins ={"http://localhost:4200/", "https://legalsystem.netlify.app/"
        , "http://localhost:80","http://localhost"} ,
        methods = {RequestMethod.GET,RequestMethod.DELETE,
                RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS,
                RequestMethod.POST},
        allowedHeaders = {"*"})
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    ContractService contractService;

    @Autowired
    ContractRepository contractRepository;
    @Autowired
    DocController docController;
    @Autowired
    private DocRepository docRepository;


    @PostMapping
    public ResponseEntity<ContractResponse> create(@RequestBody ContractCreationRequest contractRequest){
        //Redirect to view page
        ContractResponse contractResponse = contractService.create(contractRequest);
        return status(CREATED).body(contractResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contract>> listAll(){
        //Redirect to view page
        List<Contract> contractResponse = contractService.listAll();
        return ok(contractResponse);
    }
    @GetMapping("/store-codes")
    public ResponseEntity<List<Integer>> listStoreCodes(){
        List<Integer> storeCodes = contractService.listStoreCodes();
        return ok(storeCodes);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<ContractSummary>> list(
            @RequestParam(required = false) Map<String, String> filterMap){

        return ok(contractService.listByFilter(filterMap));
    }
    @GetMapping("/status")
    public ResponseEntity<ContractStatusCount> getStatusCount(){
        return ok(contractService.getStatusCount());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContractResponse> getContract(@PathVariable("id") long id) {
        ContractResponse _contract = contractService.getContractById(id);
        if ( _contract != null)
            return ok(_contract);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ContractResponse>  updateContract(@PathVariable("id") long id, @RequestBody ContractCreationRequest contract) {
        ContractResponse response = contractService.updateContract(id, contract);
        return status(OK).body(response);
    }
    @GetMapping("/del")
    public void delete(){
        contractRepository.deleteAll();
    }

    @PostMapping("/addFile/{id}")
    public ResponseEntity<ContractResponse>  updateContractFile(@PathVariable("id") long id, @RequestParam("files") MultipartFile files) throws IOException {
        ContractResponse response = contractService.addFile(id,files);
        return status(OK).body(response);
    }
    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable ("id") int id) {
        List<Contract> contracts = contractRepository.findAll();
        Contract contract = contractRepository.getById((long) id);
        int idd = contract.getAttachment().getId();
        Doc doc = docRepository.getById(contract.getAttachment().getId());
//get file from repo
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getDocType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=newfile.pdf")
                .body(new ByteArrayResource(doc.getData()));
    }

}