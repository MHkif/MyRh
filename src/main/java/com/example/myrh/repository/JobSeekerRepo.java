package com.example.myrh.repository;

import com.example.myrh.enums.UserStatus;
import com.example.myrh.model.JobSeeker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.Collection;

public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer> {
    boolean existsByEmail(String email);
    Optional<JobSeeker> findByEmail(String email);
    Page<JobSeeker> getAllByStatus(UserStatus userStatus, Pageable pageable);
}
