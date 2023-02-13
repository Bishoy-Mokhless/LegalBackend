package com.kazyonplus.contracts.controller;

import java.util.List;
import java.util.Map;

import com.kazyonplus.contracts.model.*;
import com.kazyonplus.contracts.repository.ContractRepository;
import com.kazyonplus.contracts.service.ContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin(origins ={"http://localhost:4200/", "https://legalsystem.netlify.app/"} ,
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
}