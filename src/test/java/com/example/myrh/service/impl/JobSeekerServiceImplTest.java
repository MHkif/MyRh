package com.example.myrh.service.impl;

import com.example.myrh.enums.UserStatus;
import com.example.myrh.model.JobSeeker;
import com.example.myrh.repository.JobSeekerRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension .class)
class JobSeekerServiceImplTest {

    @Mock
    private JobSeekerRepo jobSeekerRepository ;
    private JobSeeker jobSeeker;


    @BeforeEach
    void setUp() {
         this.jobSeeker= new JobSeeker();
        jobSeeker.setFirst_name("El Mehdi");
        jobSeeker.setLast_name("El Hajoujy");
        jobSeeker.setEmail("elmahdi311@gmail.com");
        jobSeeker.setPassword("testtest");
        jobSeeker.setStatus(UserStatus.ONLINE);
    }

    @AfterEach
    void tearDown() {
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

    @Test
    void filterAll() {
         // : 7-01-2024 filter jobSeeker by their state online or offline ?status=online/offline
        Map<String,String> params = new HashMap<>();
        params.put("status","online");
        params.put("page","0");
        params.put("size","10");

        String status= params.containsKey("status".toLowerCase())?params.get("status").toUpperCase():"";

        assertNotEquals("offline",status);
        assertNotEquals("online",status);
        assertEquals("ONLINE",status);

        int page = Integer.parseInt(params.getOrDefault("page","0"));
        int size = Integer.parseInt(params.getOrDefault("size","10"));
        assertEquals(0,page);
        assertEquals(10,size);


        when(this.jobSeekerRepository.save(this.jobSeeker)).thenReturn(jobSeeker);
        assertEquals(jobSeeker,this.jobSeekerRepository.save(this.jobSeeker));

    }
}