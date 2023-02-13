package com.kazyonplus.labeltrademark.controller;

import com.kazyonplus.labeltrademark.model.Label.LabelRequest;
import com.kazyonplus.labeltrademark.model.Label.LabelResponse;
import com.kazyonplus.labeltrademark.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    LabelService labelService;

    @PostMapping("/{id}")
    public ResponseEntity<LabelResponse> update(
            @PathVariable("id") Long id,
            @RequestBody LabelRequest labelRequest){
        return ok(labelService.update(labelRequest, id));
    }

    @PostMapping
    public ResponseEntity<LabelResponse> create(
            @RequestBody LabelRequest labelRequest){
        return status(CREATED).body(labelService.create(labelRequest));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable("id") Long id){
        labelService.deleteById(id);
        return ok(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LabelResponse> getById(
            @PathVariable("id") Long id){
        return ok(labelService.getById(id));
    }
    @GetMapping()
    public ResponseEntity<List<LabelResponse>> getAll(){
        return ok(labelService.getAll());
    }
    @GetMapping("/search")
    public ResponseEntity<List<LabelResponse>> search(
            @RequestParam("search_term") String searchTerm){
        return ok(labelService.search(searchTerm));
    }
    @GetMapping("/codes")
    public ResponseEntity<List<String>> listCodes(){
        List<String> codes = labelService.listCodes();
        return ok(codes);
    }
}
