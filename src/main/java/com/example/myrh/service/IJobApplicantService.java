package com.example.myrh.service;

import com.example.myrh.dto.requests.CompanyJobApplicantReq;
import com.example.myrh.dto.requests.JobApplicantReq;
import com.example.myrh.dto.responses.JobApplicantRes;
import com.example.myrh.dto.responses.OfferRes;
import com.example.myrh.model.JobApplicant;
import com.example.myrh.model.JobApplicantId;

import java.util.List;

public interface IJobApplicantService extends IService<JobApplicant, JobApplicantId, JobApplicantReq, JobApplicantRes>{
    JobApplicantRes updateStatus(CompanyJobApplicantReq req);
    List<JobApplicantRes> getAllByCompany(int companyId );
}
