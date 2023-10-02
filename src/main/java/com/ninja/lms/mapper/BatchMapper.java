package com.ninja.lms.mapper;

import com.ninja.lms.dto.BatchDto;
import com.ninja.lms.entity.Batch;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * mapping data between different Java objects DTO and entity
 * componentModel = "spring" - MapStruct generate Spring components (beans) for the mapper.
 * TargetEntity sourceToTarget(SourceDTO sourceDTO);
 */
@Mapper(componentModel = "spring")
public interface BatchMapper {
    BatchMapper BATCH_MAPPER = Mappers.getMapper(BatchMapper.class);

    @Mapping(target = "programId", source = "program.programId")
    BatchDto batchToBatchDTO(Batch batch);

    @InheritInverseConfiguration(name = "batchToBatchDTO")
    Batch batchDtoToBatch(BatchDto batchDto);

    List<BatchDto> batchesToBatchDTOs(List<Batch> baches);
}