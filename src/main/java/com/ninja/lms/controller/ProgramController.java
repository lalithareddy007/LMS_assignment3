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

    /**
     *
     * @return get list of programs
     */
    @GetMapping("/programs")
    public ResponseEntity<List<ProgramDto>> getPrograms() {
        List<ProgramDto> programList = programService.getAllPrograms();
        return ResponseEntity.status(HttpStatus.OK).body(programList);
    }

    /**
     *
     * @param programId Long
     * @return retrieves the details of a specific program
     */
    @GetMapping(path="/programId/{programId}")
    public ResponseEntity <ProgramBatchDto> getOneProgramById(@Valid @PathVariable Long programId){
        ProgramBatchDto programBatchDto = programService.fetchProgramByProgramId(programId);
        return ResponseEntity.status(HttpStatus.OK).body(programBatchDto);
    }

    /**
     *
     * @param programDto
     * @return
     */

    @PostMapping(path="/saveProgram")
    public ResponseEntity<ProgramDto> createAndSaveProgram(@Valid @RequestBody ProgramDto programDto) {
        ProgramDto newProgramDto = programService.createProgram(programDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProgramDto);
    }

    /**
     *
     * @param programId
     * @param programDto
     * @return
     */
    @PutMapping("/updateProgram/{programId}")
    public ResponseEntity<ProgramDto> updateProgram(@Valid @PathVariable Long programId, @RequestBody ProgramDto programDto){
        ProgramDto updatedProgramDto = programService.updateProgram(programId,programDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProgramDto);
    }

    /**
     *
     * @param programId
     * @return
     */
    @DeleteMapping("/deleteProgram/{programId}")
    public ResponseEntity<String> deleteProgram(@Valid @PathVariable Long programId){
        boolean deletedProgram = programService.deleteProgram(programId);
        if(deletedProgram){
            return ResponseEntity.ok().body("Program deleted");
        }
        else return ResponseEntity.ok().body("Program not found");
    }


}
