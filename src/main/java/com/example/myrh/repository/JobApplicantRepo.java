package com.example.myrh.repository;

import com.example.myrh.model.JobApplicant;
import com.example.myrh.model.JobApplicantId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicantRepo extends JpaRepository<JobApplicant, JobApplicantId> {
}
