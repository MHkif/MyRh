package com.example.myrh.repository;

import com.example.myrh.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RecruiterRepo  {
    boolean existsByEmail(String email);

    Recruiter findByEmail(String email);
}
