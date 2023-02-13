package com.kazyonplus.contracts.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.kazyonplus.contracts.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kazyonplus.contracts.repository.ContractRepository;


@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private ModelMapper mapper;
    public ContractResponse create(ContractCreationRequest creationRequest){
        Contract contract = mapper.map(creationRequest, Contract.class);
        contractRepository.save(contract);
        return mapper.map(contract, ContractResponse.class);
    }
    public List<Contract> listAll() {
        return contractRepository.findAll();
    }
    public List<Integer> listStoreCodes(){
        return contractRepository.listStoreCodes();
    }
    public ContractStatusCount getStatusCount(){
        ContractStatusCount contractStatusCount = new ContractStatusCount();
        contractStatusCount.setValid(contractRepository.countByStatus("ساري"));
        contractStatusCount.setRenewal(contractRepository.countByStatus("مطلوب تجديده"));
        contractStatusCount.setAnnulled(contractRepository.countByStatus("فسخ"));
        return contractStatusCount;
    }

    public List<ContractSummary> listByFilter(Map<String, String> filterMap){
        List<Contract> contracts = contractRepository.listByFilter(filterMap);
        List<ContractSummary> contractSummary = new LinkedList<>();
        for(Contract contract: contracts){
            contractSummary.add(mapper.map(contract, ContractSummary.class));
        }
        return contractSummary;
    }

    public ContractResponse getContractById(long contractId) {
        Optional<Contract> _contract = contractRepository.findById(contractId);

        return _contract.map(contract -> mapper.map(contract, ContractResponse.class)).orElse(null);
    }

    public ContractResponse updateContract(long contractId, ContractCreationRequest contract) {
        Optional<Contract> presentContract = contractRepository.findById(contractId);
        if (presentContract.isPresent()) {
            Contract _contract = mapper.map(contract, Contract.class);

            _contract.setId(contractId);
            _contract.setHasAttachment(presentContract.get().getHasAttachment());
            contractRepository.save(_contract);
        }

        return mapper.map(contract, ContractResponse.class);
    }
}
