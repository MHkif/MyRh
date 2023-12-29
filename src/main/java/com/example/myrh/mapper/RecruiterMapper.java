package com.example.myrh.mapper;

import com.example.myrh.dto.requests.RecruiterReq;
import com.example.myrh.dto.responses.RecruiterRes;
import com.example.myrh.model.Recruiter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecruiterMapper implements IMapper<Recruiter, RecruiterReq, RecruiterRes>{
    private final ModelMapper modelMapper;

    @Autowired
    public RecruiterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RecruiterRes toRes(Recruiter recruiter) {
        return this.modelMapper.map(recruiter, RecruiterRes.class);
    }

    @Override
    public RecruiterReq toReq(Recruiter recruiter) {
        return this.modelMapper.map(recruiter, RecruiterReq.class);
    }

    @Override
    public Recruiter resToEntity(RecruiterRes recruiterRes) {
        return this.modelMapper.map(recruiterRes, Recruiter.class);
    }

    @Override
    public Recruiter reqToEntity(RecruiterReq req) {
        return this.modelMapper.map(req, Recruiter.class);
    }
}
