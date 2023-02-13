package com.kazyonplus.labeltrademark.service;

import com.kazyonplus.labeltrademark.model.Trademark.Trademark;
import com.kazyonplus.labeltrademark.model.Trademark.TrademarkRequest;
import com.kazyonplus.labeltrademark.model.Trademark.TrademarkResponse;
import com.kazyonplus.labeltrademark.repository.TrademarkRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.getInstance;

@Service
@Log4j2
public class TrademarkService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TrademarkRepository trademarkRepository;

    public TrademarkResponse create(TrademarkRequest trademarkRequest){
        Trademark trademark = modelMapper.map(trademarkRequest, Trademark.class);
        trademarkRepository.save(trademark);
        return modelMapper.map(trademark, TrademarkResponse.class);
    }
    public TrademarkResponse update(TrademarkRequest trademarkRequest, Long id){
        Trademark trademark = new Trademark();
        if(trademarkRepository.existsById(id)){
            trademark = modelMapper.map(trademarkRequest, Trademark.class);
            trademark.setId(id);
            trademarkRepository.save(trademark);
        }
        return modelMapper.map(trademark, TrademarkResponse.class);
    }
    public void deleteById(Long id){
        trademarkRepository.deleteById(id);
    }
    public TrademarkResponse getById(Long id){
        Trademark trademark = trademarkRepository.findById(id).get();
        return modelMapper.map(trademark, TrademarkResponse.class);
    }
    public List<TrademarkResponse> getAll(){
        List<Trademark> trademarks = trademarkRepository.findAll();
        List<TrademarkResponse> trademarkResponses = new LinkedList<>();
        for(Trademark trademark: trademarks){
           trademarkResponses.add(modelMapper.map(trademark, TrademarkResponse.class));
        }
        return trademarkResponses;
    }
    public List<TrademarkResponse> search(String searchTerm){
        List<Trademark> trademarks = trademarkRepository.findAllByCodeStartsWithOrNameStartsWith(searchTerm, searchTerm);
        List<TrademarkResponse> trademarkResponses = new LinkedList<>();
        for(Trademark trademark: trademarks){
            trademarkResponses.add(modelMapper.map(trademark, TrademarkResponse.class));
        }
        return trademarkResponses;
    }
    public List<String> listCodes(){
        return trademarkRepository.listCodes();
    }
}
