package com.example.myrh.repository;

import com.example.myrh.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepo extends JpaRepository<Agent, Integer> {
    boolean existsByEmail(String email);
}
