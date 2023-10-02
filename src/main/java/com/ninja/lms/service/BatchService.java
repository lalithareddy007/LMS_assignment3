package com.ninja.lms.service;

import com.ninja.lms.dto.BatchDto;
import java.util.List;

public interface BatchService {
    BatchDto fetchBatchByBatchId(Integer batchId);
    List<BatchDto> fetchBatchByProgramId (Long programId);
    List<BatchDto> fetchAllBatches();
    BatchDto createBatch(BatchDto batchDto);
    String updateBatch (Integer batchId, Long programId);
    boolean deleteBatch (Integer batchId);

}
