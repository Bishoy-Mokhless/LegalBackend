package com.kazyonplus.warehouse.controller;

import com.kazyonplus.warehouse.model.WarehouseLicense;
import com.kazyonplus.warehouse.model.WarehouseRequest;
import com.kazyonplus.warehouse.model.WarehouseResponse;
import com.kazyonplus.warehouse.repository.WarehouseRepository;
import com.kazyonplus.warehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;

    @Autowired
    WarehouseRepository warehouseRepository;
    @PostMapping
    public ResponseEntity<WarehouseResponse> create(@RequestBody WarehouseRequest warehouseRequest){
        //Redirect to view page
        WarehouseResponse warehouseResponse = warehouseService.create(warehouseRequest);
        return status(CREATED).body(warehouseResponse);
    }
    @GetMapping("/all")
    public ResponseEntity<List<WarehouseLicense>> listAll(){
        //Redirect to view page
        List<WarehouseLicense> warehouseResponse = warehouseService.listAll();
        return ok(warehouseResponse);
    }
    @GetMapping("")
    public ResponseEntity<WarehouseResponse> listById(@RequestParam("id") long id){
        //Redirect to view page
       WarehouseResponse warehouseResponse = warehouseService.getById(id);
        return ok(warehouseResponse);
    }
    @GetMapping("/status")
    public ResponseEntity<List<WarehouseResponse>> listByStatus(
            @RequestParam("status") String status){
        return ok(warehouseService.filterByStatus(status));
    }
    @GetMapping("/address")
    public ResponseEntity<List<WarehouseResponse>> listByAddress(
            @RequestParam("address") String address){
        return ok(warehouseService.filterByAddress(address));
    }
    @PostMapping("/{id}")
    public ResponseEntity<WarehouseResponse> update(
            @PathVariable Long id,
            @RequestBody WarehouseRequest warehouseRequest){
            return ok(warehouseService.update(id, warehouseRequest));
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        warehouseRepository.deleteById(id);
    }
}
