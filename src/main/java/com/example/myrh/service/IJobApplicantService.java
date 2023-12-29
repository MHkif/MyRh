package com.example.myrh.service;

import com.example.myrh.dto.requests.JobApplicantReq;
import com.example.myrh.dto.responses.JobApplicantRes;
import com.example.myrh.model.JobApplicant;
import com.example.myrh.model.JobApplicantId;

public interface IJobApplicantService extends IService<JobApplicant, JobApplicantId, JobApplicantReq, JobApplicantRes>{
}
