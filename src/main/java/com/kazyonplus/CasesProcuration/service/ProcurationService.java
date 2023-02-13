package com.kazyonplus.CasesProcuration.service;



import com.kazyonplus.CasesProcuration.model.Procuration;
import com.kazyonplus.CasesProcuration.model.ProcurationDTO;
import com.kazyonplus.CasesProcuration.repository.ProcurationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProcurationService {
    @Autowired
    private ProcurationRepository procurationRepository;

    @Autowired
    private ModelMapper modelMapper;


    public ProcurationDTO addProcuration(Procuration proc){

        return  modelMapper.map(procurationRepository.save(proc),ProcurationDTO.class);

    }

    public ProcurationDTO findByID(Long id) {
        return modelMapper.map(procurationRepository.findById(id).orElse(null), ProcurationDTO.class);
    }

    public List<ProcurationDTO> getAllProcurations(){
        List<ProcurationDTO> list = new LinkedList<>();
        for(Procuration P: procurationRepository.findAll()){
            list.add(modelMapper.map(P, ProcurationDTO.class));
        }
        return list;
    }

    public List<ProcurationDTO> searchProcurationsByName(String name){
        List<ProcurationDTO> list = new LinkedList<>();
        for(Procuration P: procurationRepository.searchProcurationsByName(name)){
            list.add(modelMapper.map(P, ProcurationDTO.class));
        }
        return list;
    }

    public void updateProcuration(ProcurationDTO dto, Long id){
        Procuration old_Procuration=modelMapper.map(procurationRepository.findById(id).orElse(null),Procuration.class);
        old_Procuration.setProcuration_number(dto.getProcuration_number());
        old_Procuration.setOffice_procuration_number(dto.getOffice_procuration_number());
        old_Procuration.setClient_name(dto.getClient_name());
        old_Procuration.setOffice(dto.getOffice());
        old_Procuration.setYear(dto.getYear());
        procurationRepository.save(old_Procuration);
    }


    public void deleteProcuration(Long id){
        procurationRepository.deleteById(id);
    }
}
