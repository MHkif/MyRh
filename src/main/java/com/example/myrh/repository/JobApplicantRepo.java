package com.example.myrh.repository;

import com.example.myrh.model.Company;
import com.example.myrh.model.JobApplicant;
import com.example.myrh.model.JobApplicantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface JobApplicantRepo extends JpaRepository<JobApplicant, JobApplicantId> {

    //    Collection<JobApplicant> getAllById_JobSeeker_id(Integer id);
        @Query("select j from JobApplicant j where j.id.jobSeeker_id = :id")
        Collection<JobApplicant> getAllById_JobSeeker_id(Integer id);

    @Query("select j from JobApplicant j where j.id.offer_id = :id")
    Collection<JobApplicant> getAllById_Offer_id(Integer id);
    @Query("SELECT ja FROM JobApplicant ja " +
            "WHERE ja.id.offer_id IN (SELECT off.id FROM Offer off WHERE off.company.id = :companyId)")
    List<JobApplicant> findAllByCompany(@Param("companyId") int companyId);


}
