package com.ninja.lms.service;

import com.ninja.lms.dto.ProgramBatchDto;
import com.ninja.lms.dto.ProgramDto;
import java.util.List;
public interface ProgramService {
    ProgramBatchDto fetchProgramByProgramId(Long programId);
    ProgramDto createProgram(ProgramDto programDto);
    List<ProgramDto> getAllPrograms();
    ProgramDto updateProgram(Long programId,ProgramDto programDto);
    boolean deleteProgram(Long programId);

}
