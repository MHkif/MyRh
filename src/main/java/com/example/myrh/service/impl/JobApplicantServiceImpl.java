package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.JobApplicantReq;
import com.example.myrh.dto.responses.JobApplicantRes;
import com.example.myrh.mapper.JobApplicantMapper;
import com.example.myrh.model.JobApplicant;
import com.example.myrh.model.JobApplicantId;
import com.example.myrh.repository.JobApplicantRepo;
import com.example.myrh.repository.JobSeekerRepo;
import com.example.myrh.repository.OfferRepo;
import com.example.myrh.service.IJobApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobApplicantServiceImpl implements IJobApplicantService {

    private final JobApplicantRepo repository;
    private final OfferRepo offerRepo;
    private final JobSeekerRepo jobSeekerRepo;
    private final JobApplicantMapper mapper;

    @Override
    public JobApplicantRes getById(JobApplicantId id) {
        JobApplicant jobApplicant = repository.findById(id).orElseThrow(() -> new IllegalStateException("Job Applicant not found"));
        return mapper.toRes(jobApplicant);
    }

    @Override
    public Page<JobApplicantRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public JobApplicantRes create(JobApplicantReq request) {
        if(!jobSeekerRepo.existsById(request.getId().getJobSeeker_id())){
            throw new IllegalStateException("JobSeeker Not Exist");
        }

        if(!offerRepo.existsById(request.getId().getOffer_id())){
            throw new IllegalStateException("Offer Not Exist");
        }
        JobApplicant jobApplicant = repository.save(mapper.reqToEntity(request));
        return mapper.toRes(jobApplicant);
    }

    @Override
    public JobApplicantRes update(int id, JobApplicantRes request) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
