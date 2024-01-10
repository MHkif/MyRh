package com.example.myrh.repository;

import com.example.myrh.model.JobApplicant;
import com.example.myrh.model.JobApplicantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface JobApplicantRepo extends JpaRepository<JobApplicant, JobApplicantId> {

    //    Collection<JobApplicant> getAllById_JobSeeker_id(Integer id);
        @Query("select j from JobApplicant j where j.id.jobSeeker_id = :id")
        Collection<JobApplicant> getAllById_JobSeeker_id(Integer id);

    @Query("select j from JobApplicant j where j.id.offer_id = :id")
    Collection<JobApplicant> getAllById_Offer_id(Integer id);

}
