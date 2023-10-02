package com.ninja.lms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * Batch table is the child table,
 * @primaryKey - batch_id
 * @foriegnKey - batch_program_id which reference the parent table Program
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tbl_lms_batch")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="batch_id", nullable = false)
    private Integer batchId;


    @Column(name="batch_name")
    @NotBlank(message = "Batch name is required")
    private String batchName;

    @Column(name="batch_description")
    private String batchDescription;

    @Column(name="batch_status")
    @Pattern(regexp = "Active|Inactive|Beginner", message = "Batch status must be 'Active' or 'Inactive' or 'Beginner'")
    private String batchStatus;


    @ManyToOne ( fetch = FetchType.LAZY)
    @JoinColumn ( name = "batch_program_id")
    private Program program;

    @Column(name="batch_no_of_classes")
    @Min(value = 1, message = "Number of classes must be at least 1")
    Integer batchNoOfClasses;

    @Column(name="creation_time")
    @CreationTimestamp
    private Timestamp creationTime;

    @Column(name="last_mod_time")
    @UpdateTimestamp
    private Timestamp lastModTime;
}
