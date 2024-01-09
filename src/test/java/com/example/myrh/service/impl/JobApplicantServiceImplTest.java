package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.JobApplicantReq;
import com.example.myrh.dto.responses.JobApplicantRes;
import com.example.myrh.model.JobApplicant;
import com.example.myrh.model.JobApplicantId;
import com.example.myrh.model.JobSeeker;
import com.example.myrh.repository.JobApplicantRepo;
import com.example.myrh.repository.JobSeekerRepo;
import com.example.myrh.repository.OfferRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobApplicantServiceImplTest {

    @Mock
    private JobSeekerRepo jobSeekerRepo;
    @Mock
    private  JobApplicantRepo jobApplicantRepo;
    @Mock
    private OfferRepo offerRepo;
    private JobApplicant jobApplicant;
    private Page<JobApplicantRes> jobApplicantResPage;

    @BeforeEach
    void setUp() {
        this.jobApplicant = new JobApplicant();
        JobApplicantId jobApplicantId =new JobApplicantId();
        jobApplicantId.setJobSeeker_id(1);
        jobApplicantId.setOffer_id(1);
        this.jobApplicant.setId(jobApplicantId);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getById() {
        when(this.jobApplicantRepo.findById(this.jobApplicant.getId())).thenReturn(
                Optional.ofNullable(this.jobApplicant)
        );
        assertEquals
                (this.jobApplicant,
                        this.jobApplicantRepo.findById(this.jobApplicant.getId()).get());
    }

    @Test
    void getAll() {
        when(this.jobApplicantRepo.findAll()).thenReturn(
                List.of(this.jobApplicant)
        );
        assertEquals
                (List.of(this.jobApplicant),
                        this.jobApplicantRepo.findAll());

    }

    @Test
    void create() {

        //VERIFY IF JOB SEEKER EXIST Offer Not Exist
        JobApplicantReq request = new JobApplicantReq();

        request.setId(this.jobApplicant.getId());

         when(jobSeekerRepo.existsById(request.getId().getJobSeeker_id())).thenReturn(true);
        assertTrue(jobSeekerRepo.existsById(1));
        assertEquals(1,request.getId().getJobSeeker_id());



        //VERIFY IF JOB SEEKER EXIST Offer Not Exist
        when(offerRepo.existsById(request.getId().getOffer_id())).thenReturn(true);
        assertTrue(offerRepo.existsById(1));
        assertEquals(1,request.getId().getOffer_id());

        //  : verify offer is exists
        JobSeeker jobSeeker = new JobSeeker();
        jobSeeker.setFirst_name("ELMAHDI");
        jobSeeker.setLast_name("ELHJOUJY");
        //VERIFY :EMAIL IS UNIQUE
        jobSeeker.setEmail("elmahdi@email.com");

        when(jobSeekerRepo.save(jobSeeker)).thenReturn(jobSeeker);
        assertEquals(jobSeeker,jobSeekerRepo.save(jobSeeker));
        assertEquals("ELMAHDI",jobSeeker.getFirst_name());
        assertEquals("ELHJOUJY",jobSeeker.getLast_name());



        when(jobApplicantRepo.save(jobApplicant)).thenReturn(jobApplicant);
        assertEquals(jobApplicant,jobApplicantRepo.save(jobApplicant));


    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}