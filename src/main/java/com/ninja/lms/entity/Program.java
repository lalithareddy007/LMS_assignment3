package com.ninja.lms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor //default constructor
@AllArgsConstructor //parameterized constructor
@ToString
@Entity
@Table(name = "tbl_lms_program")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="program_id")
    private Long programId;

    @Column(name="program_name")
    @NotBlank(message = "Program name is required")
   // @Size(max = 100, message = "Program name cannot exceed 100 characters")
    private String programName;

    @Column(name="program_description")
    //@Size(max = 255, message = "Program description cannot exceed 255 characters")
    private String programDescription;

    @Column(name="program_status")
    @Pattern(regexp = "Active|Inactive|Beginner", message = "Program status must be 'Active' or 'Inactive' or 'Beginner ")
    String programStatus;

    @Column(name="creation_time")
    @CreationTimestamp
    private Timestamp creationTime;

    @Column(name="last_mod_time")
    @UpdateTimestamp
    private Timestamp lastModTime;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL)
    private List<Batch> batches = new ArrayList<>();

}
