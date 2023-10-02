package com.ninja.lms.service.impl;

import com.ninja.lms.dto.BatchDto;
import com.ninja.lms.dto.ProgramBatchDto;
import com.ninja.lms.dto.ProgramDto;
import com.ninja.lms.entity.Batch;
import com.ninja.lms.entity.Program;
import com.ninja.lms.mapper.BatchMapper;
import com.ninja.lms.mapper.ProgramMapper;
import com.ninja.lms.repository.ProgramRepository;
import com.ninja.lms.service.BatchService;
import com.ninja.lms.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    ProgramRepository programRepository;
    @Autowired
    ProgramMapper programMapper;
    @Autowired
    BatchMapper batchMapper;
    @Autowired
    BatchService batchService;

    /**
     * @param programId fetch program by using programId
     * @return program with all batch List
     */
    @Override
    public ProgramBatchDto fetchProgramByProgramId(Long programId) {
        Program program = programRepository.findById(programId).orElseThrow();
        return programMapper.programToProgramBatchDto(program);
    }

    /**
     * @param programDto given program request
     * @return created and saved program in repo
     */
    @Override
    public ProgramDto createProgram(ProgramDto programDto) {
        Program program = programMapper.programDtoToProgramEntity(programDto);

        Program newProgram = programRepository.save(program);

        ProgramDto programDto1 = programMapper.programToProgramDTO(newProgram);
        List<Batch> batchList = newProgram.getBatches();
        for (Batch batch : batchList) {

            batch.setProgram(newProgram); // Set the Program reference
            BatchDto newBatch = batchMapper.batchToBatchDTO(batch);
            batchService.createBatch(newBatch);

    }


        return programDto1;
}

    /**
     * @return fetch all programs in repo
     */
    @Override
    public List<ProgramDto> getAllPrograms() {
        List<Program> programEntityList= programRepository.findAll();
        //log.info("getAllPrograms:" +programEntityList);
        return programMapper.ProgramsToProgramDtos(programEntityList);
    }

    /**
     *
     * @param programDto given updated program
     * @return updated program
     */
    @Override
    public ProgramDto updateProgram(ProgramDto programDto) {
        Program program = programMapper.programDtoToProgramEntity(programDto);
        Program updatedProgram = programRepository.save(program);

        return programMapper.programToProgramDTO(updatedProgram);
    }

    /**
     * @param programId
     * @return
     */
    @Override
    public boolean deleteProgram(Long programId) {
        //find program with given programId
        programRepository.findById(programId).orElseThrow();
        programRepository.deleteById(programId);
        return true;
    }
}
