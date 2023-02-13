package com.kazyonplus.warehouse.service;

import com.kazyonplus.warehouse.model.WarehouseLicense;
import com.kazyonplus.warehouse.model.WarehouseRequest;
import com.kazyonplus.warehouse.repository.WarehouseRepository;
import com.kazyonplus.warehouse.model.WarehouseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ModelMapper mapper;

    public WarehouseResponse create(WarehouseRequest creationRequest){
        WarehouseLicense warehouse = mapper.map(creationRequest, WarehouseLicense.class);
        warehouseRepository.save(warehouse);
        return mapper.map(warehouse, WarehouseResponse.class);
    }
    public List<WarehouseLicense> listAll() {
        return warehouseRepository.findAll();
    }
//    public List<WarehouseLicense> filterByStatus() {
//
//    }
    public List<WarehouseResponse> filterByStatus(String status){
        List<WarehouseResponse> list = new LinkedList<>();
        for(WarehouseLicense P: warehouseRepository.searchWarehouseByStatus(status)){
            list.add(mapper.map(P, WarehouseResponse.class));
        }
        return list;
    }
    public List<WarehouseResponse> filterByAddress(String address){
        List<WarehouseResponse> list = new LinkedList<>();
        for(WarehouseLicense P: warehouseRepository.searchWarehouseByAddress(address)){
            list.add(mapper.map(P, WarehouseResponse.class));
        }
        return list;
    }
    public WarehouseResponse getById(long id) {
        return mapper.map(warehouseRepository.findById(id).orElse(null),WarehouseResponse.class);

    }
    public WarehouseResponse update(long id, WarehouseRequest warehouseRequest){

        Optional<WarehouseLicense> presentWarehouse = warehouseRepository.findById(id);

        if (presentWarehouse.isPresent()) {
            WarehouseLicense _warehouse = mapper.map(warehouseRequest, WarehouseLicense.class);

            _warehouse.setId(presentWarehouse.get().getId());
            warehouseRepository.save(_warehouse);
        }
        return mapper.map(warehouseRequest, WarehouseResponse.class);
    }

    public void delete(long id){
        warehouseRepository.deleteById(id);
    }
}
