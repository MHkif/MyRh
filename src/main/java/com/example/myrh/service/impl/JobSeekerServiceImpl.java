package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.JobSeekerReq;
import com.example.myrh.dto.responses.JobSeekerRes;
import com.example.myrh.mapper.JobSeekerMapper;
import com.example.myrh.model.JobSeeker;
import com.example.myrh.repository.JobSeekerRepo;
import com.example.myrh.service.IJobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class JobSeekerServiceImpl implements IJobSeekerService {

    private final JobSeekerRepo repository;
    private final JobSeekerMapper mapper;

    @Autowired
    public JobSeekerServiceImpl(JobSeekerRepo repository, JobSeekerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public JobSeekerRes getById(Integer id) {
        JobSeeker jobSeeker = repository.findById(id).orElseThrow(() -> new IllegalStateException("jobSeeker not found"));
        return mapper.toRes(jobSeeker);
    }

    @Override
    public Page<JobSeekerRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public JobSeekerRes create(JobSeekerReq request) {
        if(repository.existsByEmail(request.getEmail())){
            throw new IllegalStateException("Email Already Taken");
        }

        JobSeeker jobSeeker = repository.save(mapper.reqToEntity(request));
        return mapper.toRes(jobSeeker);
    }

    @Override
    public JobSeekerRes update(int id, JobSeekerRes request) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
