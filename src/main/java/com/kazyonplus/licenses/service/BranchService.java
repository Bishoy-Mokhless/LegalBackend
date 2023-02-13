package com.kazyonplus.licenses.service;

import com.kazyonplus.licenses.model.Branch.Branch;
import com.kazyonplus.licenses.model.Branch.BranchRequest;
import com.kazyonplus.licenses.model.Branch.BranchResponse;
import com.kazyonplus.licenses.model.Branch.BranchSummary;
import com.kazyonplus.licenses.model.License.ExternalApproval;
import com.kazyonplus.licenses.model.License.License;
import com.kazyonplus.licenses.model.License.LicenseRequest;
import com.kazyonplus.licenses.model.License.LicenseResponse;
import com.kazyonplus.licenses.repository.BranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BranchService{
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BranchResponse create(BranchRequest branchRequest) {
        Branch branch = modelMapper.map(branchRequest, Branch.class);
        branchRepository.save(branch);
        BranchResponse branchResponse = modelMapper.map(branch, BranchResponse.class) ;
        return branchResponse;
    }

    public List<BranchSummary> listAll(){
        List<Branch> branchList = branchRepository.findAll() ;
        List<BranchSummary> branchSummaryList = new LinkedList<>() ;
        for(Branch branch : branchList){
            branchSummaryList.add(modelMapper.map(branch,BranchSummary.class)) ;
        }
        return  branchSummaryList ;
    }

    public BranchResponse getById(Long id){
        Branch branch = branchRepository.findById(id).get();
        return modelMapper.map(branch, BranchResponse.class) ;
    }

    public BranchResponse update(Long id, BranchRequest branchRequest){
        Optional<Branch> branch = branchRepository.findById(id) ;
        Branch _branch = null;
        if (branch.isPresent()) {
            _branch = modelMapper.map(branchRequest, Branch.class);
            _branch.setId(branch.get().getId());
            branchRepository.save(_branch) ;
        }
        return modelMapper.map(_branch, BranchResponse.class) ;
    }
    public BranchResponse addAdvertisementLicense(Long id, LicenseRequest licenseRequest){
        Branch branch = branchRepository.findById(id).get();
        branch.setAdvertisementLicense(modelMapper.map(licenseRequest, License.class));
        branch.setId(id);
        branchRepository.save(branch);
        return modelMapper.map(branch, BranchResponse.class);
    }
    public BranchResponse addWorkingLicense(Long id, LicenseRequest licenseRequest){
        Branch branch = branchRepository.findById(id).get();
        branch.setWorkingLicense(modelMapper.map(licenseRequest, License.class));
        branch.setId(id);
        branchRepository.save(branch);
        return modelMapper.map(branch, BranchResponse.class);
    }
    public LicenseResponse getAdvertisementLicense(Long id){
        Branch branch = branchRepository.findById(id).get();
        return modelMapper.map(branch.getAdvertisementLicense(), LicenseResponse.class);
    }
    public LicenseResponse getWorkingLicense(Long id){
        Branch branch = branchRepository.findById(id).get();
        return modelMapper.map(branch.getWorkingLicense(), LicenseResponse.class);
    }
    public List<BranchSummary> listByFilter(Map<String, String> filterMap){
        List<Branch> branchList = branchRepository.listByFilter(filterMap);
        List<BranchSummary> branchSummaryList = new LinkedList<>();
        for(Branch branch: branchList){
            branchSummaryList.add(modelMapper.map(branch, BranchSummary.class));
        }
        return branchSummaryList;
    }

    public List<BranchSummary> search(String searchTerm) {
        List<Branch> branchList = branchRepository.findBranchesBySapCodeStartsWithOrStoreCodeStartsWith(searchTerm, searchTerm);
        List<BranchSummary> branchSummaryList = new LinkedList<>();
        for(Branch branch: branchList){
            branchSummaryList.add(modelMapper.map(branch, BranchSummary.class));
        }
        return branchSummaryList;
    }
    public BranchResponse addExternalApproval(Long id, ExternalApproval externalApproval){
        Branch branch = branchRepository.findById(id).get();
        List<ExternalApproval> externalApprovals = branch.getExternalApprovals();
        externalApprovals.add(externalApproval);
        branch.setExternalApprovals(externalApprovals);
        return modelMapper.map(branch, BranchResponse.class);
    }
    public List<ExternalApproval> getExternalApprovals(Long id){
        return branchRepository.findById(id).get().getExternalApprovals();
    }
}
