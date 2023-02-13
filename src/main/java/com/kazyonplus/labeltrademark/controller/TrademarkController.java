package com.kazyonplus.labeltrademark.controller;

import com.kazyonplus.labeltrademark.model.Label.LabelRequest;
import com.kazyonplus.labeltrademark.model.Label.LabelResponse;
import com.kazyonplus.labeltrademark.model.Trademark.TrademarkRequest;
import com.kazyonplus.labeltrademark.model.Trademark.TrademarkResponse;
import com.kazyonplus.labeltrademark.service.TrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/trademark")
public class TrademarkController {

    @Autowired
    TrademarkService trademarkService;

    @PostMapping("/{id}")
    public ResponseEntity<TrademarkResponse> update(
            @PathVariable("id") Long id,
            @RequestBody TrademarkRequest trademarkRequest){
        return ok(trademarkService.update(trademarkRequest, id));
    }

    @PostMapping
    public ResponseEntity<TrademarkResponse> create(
            @RequestBody TrademarkRequest trademarkRequest){
        return status(CREATED).body(trademarkService.create(trademarkRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable("id") Long id){
        trademarkService.deleteById(id);
        return ok(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TrademarkResponse> getById(
            @PathVariable("id") Long id){
        return ok(trademarkService.getById(id));
    }
    @GetMapping()
    public ResponseEntity<List<TrademarkResponse>> getAll(){
        return ok(trademarkService.getAll());
    }
    @GetMapping("/search")
    public ResponseEntity<List<TrademarkResponse>> search(
            @RequestParam("search_term") String searchTerm){
        return ok(trademarkService.search(searchTerm));
    }
    @GetMapping("/codes")
    public ResponseEntity<List<String>> listCodes(){
        List<String> codes = trademarkService.listCodes();
        return ok(codes);
    }
}
