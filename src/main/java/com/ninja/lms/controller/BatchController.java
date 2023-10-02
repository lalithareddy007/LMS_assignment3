package com.ninja.lms.controller;

import com.ninja.lms.dto.BatchDto;
import com.ninja.lms.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/batch") //base path
@Validated
@Slf4j
public class BatchController {
    @Autowired
    BatchService batchService;

    @GetMapping("/batchId/{batchId}")
    public ResponseEntity<BatchDto> getBatchById (@Valid @PathVariable Integer batchId) {
        BatchDto batchDto = batchService.fetchBatchByBatchId(batchId);
        return ResponseEntity.status(HttpStatus.OK).body(batchDto);
    }
    @GetMapping("/batches/{programId}")
    public ResponseEntity<List<BatchDto>> getBatchesByProgramId (@Valid @PathVariable Long programId){
        List<BatchDto> batchDtoList = batchService.fetchBatchByProgramId(programId);
        return ResponseEntity.status(HttpStatus.OK).body(batchDtoList);
    }
    @GetMapping("/batches")
    public ResponseEntity<List<BatchDto>> getAllBatches(){
        List<BatchDto> batchDtoList = batchService.fetchAllBatches();
        return ResponseEntity.status(HttpStatus.OK).body(batchDtoList);

    }
    @PostMapping("/saveBatch")
    public ResponseEntity<BatchDto> saveBatch (@Valid @RequestBody BatchDto batchDto){
        BatchDto newBatchDto = batchService.createBatch(batchDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBatchDto);

    }
    @PutMapping("/updateBatch/{batchId}/program/{programId}")
    public ResponseEntity<String> updateBatch (@Valid @PathVariable Integer batchId,@Valid @PathVariable Long programId){
        String s = batchService.updateBatch(batchId,programId);
        return ResponseEntity.status(HttpStatus.OK).body(s);
    }
    @DeleteMapping("/deleteBatch/{batchId}")
    public ResponseEntity<String> deleteBatch (@Valid @PathVariable Integer batchId){
        boolean deletedBatch = batchService.deleteBatch(batchId);
        if(deletedBatch){
            return ResponseEntity.ok().body("Batch deleted");
        }
        else return ResponseEntity.ok().body("Batch not found");

    }



}
