package com.ninja.lms.service.impl;

import com.ninja.lms.dto.BatchDto;
import com.ninja.lms.dto.ProgramBatchDto;
import com.ninja.lms.dto.ProgramDto;
import com.ninja.lms.entity.Batch;
import com.ninja.lms.entity.Program;
import com.ninja.lms.mapper.BatchMapper;
import com.ninja.lms.mapper.ProgramMapper;
import com.ninja.lms.repository.BatchRepository;
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
    BatchRepository batchRepository;
    @Autowired
    BatchMapper batchMapper;
    @Autowired
    BatchService batchService;

    /**
     * @param programId
     * @return
     */
    @Override
    public ProgramBatchDto fetchProgramByProgramId(Long programId) {
        Program program = programRepository.findById(programId).orElseThrow();
        return programMapper.programToProgramBatchDto(program);
    }

    /**
     * @param programDto
     * @return
     */
    @Override
    public ProgramDto createProgram(ProgramDto programDto) {
        Program program = programMapper.programDtoToProgramEntity(programDto);
        //log.info("Program request details :"+ program);

//        Program program1 = new Program();
//        program1.setProgramName(program.getProgramName());
//        program1.setProgramDescription(program.getProgramDescription());
//        program1.setProgramStatus(program.getProgramStatus());

        Program newProgram = programRepository.save(program);
        //log.info("New Program details:" + newProgram);

        ProgramDto programDto1 = programMapper.programToProgramDTO(newProgram);
        List<Batch> batchList = newProgram.getBatches();
        for (Batch batch : batchList) {
            //Batch batch = batchMapper.batchDtoToBatch(batchDto);
            batch.setProgram(newProgram); // Set the Program reference
            BatchDto newBatch = batchMapper.batchToBatchDTO(batch);
            batchService.createBatch(newBatch);

    }


        return programDto1;
}

    /**
     * @return
     */
    @Override
    public List<ProgramDto> getAllPrograms() {
        List<Program> programEntityList= programRepository.findAll();
        //log.info("getAllPrograms:" +programEntityList);
        return programMapper.ProgramsToProgramDtos(programEntityList);
    }

    /**
     * @param programId
     * @param programDto
     * @return
     */
    @Override
    public ProgramDto updateProgram(Long programId, ProgramDto programDto) {
        Program program = programMapper.programDtoToProgramEntity(programDto);
        Program oldProgram = programRepository.findById(program.getProgramId()).orElseThrow();
        oldProgram.setProgramId(programId);
        //check is there any existing program with given programId
        boolean existingBatch = programRepository.existsById(programId);
        Program updatedProgram = programRepository.save(oldProgram);

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
