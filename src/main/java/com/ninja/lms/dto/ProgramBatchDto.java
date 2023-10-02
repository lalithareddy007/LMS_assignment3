package com.ninja.lms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramBatchDto {

    private Long programId;
    @NotBlank(message = "Program name is required")
    @Pattern(regexp = "[a-zA-Z0-9 ]", message = "Program name has illegal characters")
    private String programName;

    //@Size(max = 255, message = "Program description cannot exceed 255 characters")
    private String programDescription;

    @Pattern(regexp = "Active|Inactive|Beginner", message = "Program status must be 'Active' or 'Inactive' or 'Beginner ")
    private String programStatus;
    @CreationTimestamp
    private Timestamp creationTime;
    @CreationTimestamp
    private Timestamp lastModTime;
    private List<BatchDto> batches;

}
