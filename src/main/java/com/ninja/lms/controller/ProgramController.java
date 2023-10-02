package com.ninja.lms.controller;

import com.ninja.lms.dto.ProgramBatchDto;
import com.ninja.lms.dto.ProgramDto;
import com.ninja.lms.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/program")
@Validated
public class ProgramController {
    @Autowired
    ProgramService programService;


    @GetMapping("/programs")
    public ResponseEntity<List<ProgramDto>> getPrograms() {
        List<ProgramDto> programList = programService.getAllPrograms();
        return ResponseEntity.status(HttpStatus.OK).body(programList);
    }


    @GetMapping(path="/programId/{programId}")
    public ResponseEntity <ProgramBatchDto> getOneProgramById(@Valid @PathVariable Long programId){
        ProgramBatchDto programBatchDto = programService.fetchProgramByProgramId(programId);
        return ResponseEntity.status(HttpStatus.OK).body(programBatchDto);
    }


    @PostMapping(path="/saveProgram")
    public ResponseEntity<ProgramDto> createAndSaveProgram(@Valid @RequestBody ProgramDto programDto) {
        ProgramDto newProgramDto = programService.createProgram(programDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProgramDto);
    }


    @PutMapping("/updateProgram")
    public ResponseEntity<ProgramDto> updateProgram( @RequestBody ProgramDto programDto){
        ProgramDto updatedProgramDto = programService.updateProgram(programDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProgramDto);
    }

    @DeleteMapping("/deleteProgram/{programId}")
    public ResponseEntity<String> deleteProgram(@Valid @PathVariable Long programId){
        boolean deletedProgram = programService.deleteProgram(programId);
        if(deletedProgram){
            return ResponseEntity.ok().body("Program deleted");
        }
        else return ResponseEntity.ok().body("Program not found");
    }


}
