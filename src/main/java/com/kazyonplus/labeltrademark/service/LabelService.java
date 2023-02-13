package com.kazyonplus.labeltrademark.service;

import com.kazyonplus.labeltrademark.model.Label.Label;
import com.kazyonplus.labeltrademark.model.Label.LabelRequest;
import com.kazyonplus.labeltrademark.model.Label.LabelResponse;
import com.kazyonplus.labeltrademark.repository.LabelRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import static java.util.Calendar.*;

@Service
@Log4j2
public class LabelService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LabelRepository labelRepository;

    public LabelResponse create(LabelRequest labelRequest){
        Label label = modelMapper.map(labelRequest, Label.class);
        labelRepository.save(label);
        return modelMapper.map(label, LabelResponse.class);
    }
    public LabelResponse update(LabelRequest labelRequest, Long id){
        Label label = new Label();
        if(labelRepository.existsById(id)){
            label = modelMapper.map(labelRequest, Label.class);
            label.setId(id);
            labelRepository.save(label);
        }
        return modelMapper.map(label, LabelResponse.class);
    }
    public void deleteById(Long id){
        labelRepository.deleteById(id);
    }
    public LabelResponse getById(Long id){
        Label label = labelRepository.findById(id).get();
        return modelMapper.map(label, LabelResponse.class);
    }
    public List<LabelResponse> getAll(){
        List<Label> labels = labelRepository.findAll();
        List<LabelResponse> labelResponses = new LinkedList<>();
        for(Label label: labels){
           labelResponses.add(modelMapper.map(label, LabelResponse.class));
        }
        return labelResponses;
    }
    public List<LabelResponse> search(String searchTerm){
        List<Label> labels = labelRepository.findAllByCodeStartsWithOrNameStartsWith(searchTerm, searchTerm);
        List<LabelResponse> labelResponses = new LinkedList<>();
        for(Label label: labels){
            labelResponses.add(modelMapper.map(label, LabelResponse.class));
        }
        return labelResponses;
    }
    public List<String> listCodes(){
        return labelRepository.listCodes();
    }
}
