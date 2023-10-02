package com.ninja.lms.repository;

import com.ninja.lms.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * spring data jpa provides all CRUD methods
 */

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findByProgramNameContainingIgnoreCaseOrderByProgramIdAsc(String programName);
}
