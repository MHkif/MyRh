package com.example.myrh.repository;

import com.example.myrh.model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer> {
    boolean existsByEmail(String email);

    Optional<JobSeeker> findByEmail(String email);
}
