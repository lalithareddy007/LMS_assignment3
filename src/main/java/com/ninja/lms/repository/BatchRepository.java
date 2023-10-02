package com.ninja.lms.repository;

import com.ninja.lms.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * spring data jpa provides all CRUD methods
 */


@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {
    // Use a custom query to retrieve batches by programId
    @Query("SELECT b FROM Batch b WHERE b.program.id = :programId")
    List<Batch> findByProgramId(Long programId);
}
