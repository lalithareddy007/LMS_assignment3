package com.ninja.lms.service.impl;

import com.ninja.lms.dto.BatchDto;
import com.ninja.lms.entity.Batch;
import com.ninja.lms.entity.Program;
import com.ninja.lms.mapper.BatchMapper;
import com.ninja.lms.repository.BatchRepository;
import com.ninja.lms.repository.ProgramRepository;
import com.ninja.lms.service.BatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BatchServiceImpl implements BatchService {
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    ProgramRepository programRepository;
    @Autowired
    BatchMapper batchMapper;

    /**
     * @param batchId fetch BatchDto by batchId
     * @return BatchDto
     */
    @Override
    public BatchDto fetchBatchByBatchId(Integer batchId) {
        Batch batch = null;
        if(batchId!=null){
            batch = batchRepository.findById(batchId).orElseThrow();
        }

        return batchMapper.batchToBatchDTO(batch);
    }

    /**
     * @param programId fetch Batch by using programId
     * @return List of BatchDto
     */
    @Override
    public List<BatchDto> fetchBatchByProgramId(Long programId) {
        List<Batch> batches = batchRepository.findByProgramId(programId);
        return batchMapper.batchesToBatchDTOs(batches);
    }

    /**
     * @return all Batches in repo
     */
    @Override
    public List<BatchDto> fetchAllBatches() {
        List<Batch> batches = batchRepository.findAll();
        return batchMapper.batchesToBatchDTOs(batches);
    }

    /**
     * @param batchDto use BatchDto, check Program exist and add to it's Batch List
     * @return BatchDto
     */
    @Override
    public BatchDto createBatch(BatchDto batchDto) {
        //fetch programId from the given batchDto
        Long programId = batchDto.getProgramId();
        if(programId==null){
            throw new IllegalArgumentException("The given programID must not be null");
        }
        //check repository for program with programId
        boolean b = programRepository.existsById(programId);
        Batch batchRequest = new Batch();
        if (b){
            batchRequest = batchMapper.batchDtoToBatch(batchDto);

            Program program = programRepository.findById(programId).orElseThrow();
            List<Batch> batchList = program.getBatches();
            batchList.add(batchRequest);
        }
        Batch newBatch = batchRepository.save(batchRequest);

        //finally convert newBatch to batchDto
        return batchMapper.batchToBatchDTO(newBatch);
    }

    /**
     *
     * @param batchId set given programId to the batch
     * @param programId use programId to check exist or not
     * @return batch is updated by programId
     */
    @Override
    public String updateBatch(Integer batchId, Long programId) {
        try {
            boolean b = batchRepository.existsById(batchId) && programRepository.existsById(programId);

            if (b) {
                Batch batch = batchRepository.findById(batchId).orElseThrow();
                Program program = new Program();
                program.setProgramId(programId);
                batch.setProgram(program);
                batchRepository.save(batch);
                return "Batch program updated successfully.";
            } else {
                return "Batch Program update failed. Check batchId or programId";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error updating batch program.";
        }
    }

    /**
     * @param batchId use batchId to delete batch
     * @return true if deleted
     */
    @Override
    public boolean deleteBatch(Integer batchId) {
        batchRepository.findById(batchId).orElseThrow();
        batchRepository.deleteById(batchId);
        return true;
    }
}
