package com.example.myrh.service.impl;

import com.example.myrh.enums.JobApplicationStatus;
import com.example.myrh.model.JobApplicant;
import com.example.myrh.model.JobApplicantId;
import com.example.myrh.model.JobSeeker;
import com.example.myrh.repository.JobApplicantRepo;
import com.example.myrh.repository.JobSeekerRepo;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jooq.JooqTest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IOfferServiceImplTest {


    @Mock
    JobSeekerRepo jobSeekerRepo;
    @Mock
    JobApplicantRepo jobApplicantRepo;
    private JobSeeker jobSeeker;
    @BeforeEach
    void setUp() {
        jobSeeker = new JobSeeker();
        jobSeeker.setId(1);
        jobSeeker.setFirst_name("test");
        jobSeeker.setLast_name("test");

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void search() {
    }

    @Test
    void updateVisibility() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test()
    @Description("Test get candidates offer insights")
    void getCandidatesOfferInsights() {
        int seekerId = 1;
        assertEquals(1, seekerId);
        assertTrue(seekerId>0);
        assertFalse(seekerId<0);
        //find jobSeeker by id ;
        when(this.jobSeekerRepo.findById(seekerId)).thenReturn(Optional.of(this.jobSeeker));
        assertNotNull(this.jobSeekerRepo.findById(seekerId).get());
        assertEquals(this.jobSeekerRepo.findById(seekerId).get().getId(), seekerId);
        assertEquals(this.jobSeekerRepo.findById(seekerId).get().getFirst_name(), "test");
        when(this.jobApplicantRepo.getAllById_JobSeeker_id(seekerId)).thenReturn(List.of(getJobApplication()));
        Collection<JobApplicant> jobApplicants = this.jobApplicantRepo.getAllById_JobSeeker_id(seekerId);
        assertNotNull(jobApplicants);
        assertEquals(jobApplicants.size(), 1);
        assertTrue(!jobApplicants.isEmpty());
        assertFalse(jobApplicants.isEmpty());
        jobApplicants.forEach(jobApplicant -> {
            assertEquals(jobApplicant.getStatus()== JobApplicationStatus.ACCEPTED, true);
        });



    }
    private JobApplicant getJobApplication(){
        JobApplicantId jobApplicantId = new JobApplicantId();
        jobApplicantId.setJobSeeker_id(1);
        jobApplicantId.setOffer_id(1);
        JobApplicant jobApplicant = new JobApplicant();
        jobApplicant.setId(jobApplicantId);
        jobApplicant.setResume("path/to/resume");
        jobApplicant.setStatus(JobApplicationStatus.ACCEPTED);
        jobApplicant.setIsViewed(true);



        return jobApplicant;


    }

    @Test
    void testGetCandidatesOfferInsights() {
    }
}