package com.example.myrh.repository;

import com.example.myrh.model.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepo extends JpaRepository<Confirmation, Long> {
    Confirmation findByToken(String token);
}
