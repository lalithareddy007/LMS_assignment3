package com.ninja.lms.dto;

import javax.validation.constraints.*;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

/**
 * Entity class validation ensures the integrity of data stored in the database.
 * DTO class validation ensures that the data received from clients or
 * other sources is valid before processing it further within your application.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BatchDto {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer batchId;

    @Pattern(regexp = "[a-zA-Z0-9 ]", message = "Batch name has illegal characters")
    private String batchName;

    @Pattern(regexp = "[a-zA-Z0-9 ]", message = "Batch name has illegal characters")
    private String batchDescription;

    @Pattern(regexp = "Active|Inactive|Beginner", message = "Batch status must be 'Active' or 'Inactive' or 'Beginner'")
    private String batchStatus;

    @Min(value = 1, message = "Number of classes must be at least 1")
    private int batchNoOfClasses;

    @NotNull (message = " ProgramId should not be null" )
    private Long programId;

    @CreationTimestamp
    private Timestamp creationTime;

    @UpdateTimestamp
    private Timestamp lastModTime;



}
