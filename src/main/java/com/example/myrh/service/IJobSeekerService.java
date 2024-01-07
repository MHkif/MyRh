package com.example.myrh.service;

import com.example.myrh.dto.requests.JobSeekerReq;
import com.example.myrh.dto.responses.JobSeekerRes;
import com.example.myrh.model.JobSeeker;

public interface IJobSeekerService extends IService<JobSeeker, Integer, JobSeekerReq, JobSeekerRes>{
    JobSeekerRes auth(String email, String password);
}
