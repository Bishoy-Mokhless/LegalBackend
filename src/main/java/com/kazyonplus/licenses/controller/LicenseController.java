package com.kazyonplus.licenses.controller;

import com.kazyonplus.licenses.service.BranchService;
import com.kazyonplus.licenses.model.Branch.BranchResponse;
import com.kazyonplus.licenses.model.License.LicenseRequest;
import com.kazyonplus.licenses.model.License.LicenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/license")
public class LicenseController {
    @Autowired
    BranchService branchService;

    @PostMapping("/advertisement/{id}")
    public ResponseEntity<BranchResponse> addAdvertisementLicense(
            @PathVariable Long id,
            @RequestBody LicenseRequest licenseRequest){
        return ok(branchService.addAdvertisementLicense(id, licenseRequest));
    }

    @PostMapping("/working/{id}")
    public ResponseEntity<BranchResponse> addWorkingLicense(
            @PathVariable Long id,
            @RequestBody LicenseRequest licenseRequest){
        return ok(branchService.addWorkingLicense(id, licenseRequest));
    }

    @GetMapping("/advertisement/{id}")
    public ResponseEntity<LicenseResponse> getAdvertisementLicense(
            @PathVariable Long id,
            @RequestBody LicenseRequest licenseRequest){
        return ok(branchService.getAdvertisementLicense(id));
    }

    @GetMapping("/working/{id}")
    public ResponseEntity<LicenseResponse> getWorkingLicense(
            @PathVariable Long id,
            @RequestBody LicenseRequest licenseRequest){
        return ok(branchService.getWorkingLicense(id));
    }
}
