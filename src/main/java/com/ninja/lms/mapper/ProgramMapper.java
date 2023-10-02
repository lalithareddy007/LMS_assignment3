package com.ninja.lms.mapper;

import com.ninja.lms.dto.BatchDto;
import com.ninja.lms.dto.ProgramBatchDto;
import com.ninja.lms.dto.ProgramDto;
import com.ninja.lms.entity.Batch;
import com.ninja.lms.entity.Program;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProgramMapper {
    ProgramMapper PROGRAM_MAPPER = Mappers.getMapper(ProgramMapper.class);

    @Mapping(target = "programId", source = "program.programId")
    ProgramDto programToProgramDTO(Program program);

    @Mapping(target = "batches", expression = "java(mapBatches(program.getBatches(), program.getProgramId()))")
    ProgramBatchDto programToProgramBatchDto(Program program);
    @InheritInverseConfiguration(name = "programToProgramDTO")
    Program programDtoToProgramEntity(ProgramDto progDTO);
    List<ProgramDto> ProgramsToProgramDtos(List<Program> programEntities);

    // Custom method to map List<Batch> to List<BatchDto> with programId
    //iterate through each Batch in the list and create a corresponding BatchDto. We then set the programId for each BatchDto.
    default List<BatchDto> mapBatches(List<Batch> batches, Long programId) {
        return batches.stream()
                .map(batch -> {
                    BatchDto batchDto = new BatchDto();
                    // Map batch properties to batchDto
                    batchDto.setBatchId(batch.getBatchId());
                    batchDto.setBatchName(batch.getBatchName());
                    batchDto.setBatchDescription(batch.getBatchDescription());
                    batchDto.setBatchStatus(batch.getBatchStatus());
                    batchDto.setBatchNoOfClasses(batch.getBatchNoOfClasses());
                    batchDto.setCreationTime(batch.getCreationTime());
                    batchDto.setLastModTime(batch.getLastModTime());
                    // Set the programId for each BatchDto
                    batchDto.setProgramId(programId);

                    return batchDto;
                })
                .collect(Collectors.toList());
    }

}
