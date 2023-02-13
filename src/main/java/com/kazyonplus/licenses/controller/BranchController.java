package com.kazyonplus.licenses.controller;

import com.kazyonplus.licenses.model.License.ExternalApproval;
import com.kazyonplus.licenses.service.BranchService;
import com.kazyonplus.licenses.model.Branch.BranchRequest;
import com.kazyonplus.licenses.model.Branch.BranchResponse;
import com.kazyonplus.licenses.model.Branch.BranchSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/v1/branch")

public class BranchController {

    @Autowired
    BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchResponse> create(@RequestBody BranchRequest branchRequest){
        BranchResponse branchResponse = branchService.create(branchRequest) ;
        return status(CREATED).body(branchResponse);
    }
    @GetMapping("/all")
    //FIXME:for dashboard setup
    public ResponseEntity<List<BranchSummary>> listAll(){
        List<BranchSummary> branchSummaryList = branchService.listAll() ;
        return ok(branchSummaryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchResponse> getBranch(@PathVariable("id") Long id){
        BranchResponse branchResponse = branchService.getById(id) ;
        return ok(branchResponse) ;
    }

    @PostMapping("/{id}")
    public ResponseEntity<BranchResponse> updateBranch(@PathVariable("id") Long id,
                                                       @RequestBody BranchRequest branchRequest){
        BranchResponse branchResponse = branchService.update(id, branchRequest) ;
        return ok(branchResponse);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<BranchSummary>> listByFilter(
            @RequestParam (required = false) Map<String, String> filterMap){
        return ok(branchService.listByFilter(filterMap));
    }
    @GetMapping("/search")
    public ResponseEntity<List<BranchSummary>> search(
            @RequestParam String searchTerm){
        return ok(branchService.search(searchTerm));
    }
    @GetMapping("/{id}/external")
    public ResponseEntity<List<ExternalApproval>> getExternalApprovals(
            @PathVariable Long id){
        return ok(branchService.getExternalApprovals(id));
    }
    @PostMapping("{id}/external")
    public ResponseEntity<BranchResponse> addExternalApproval(
            @PathVariable Long id,
            @RequestBody ExternalApproval externalApproval){
            return ok(branchService.addExternalApproval(id, externalApproval));
    }
}
