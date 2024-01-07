package com.example.myrh.service;

import com.example.myrh.dto.requests.JobSeekerReq;
import com.example.myrh.dto.responses.JobSeekerRes;
import com.example.myrh.model.JobSeeker;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IJobSeekerFilterService   {
    Page<JobSeekerRes> filterAll(Map<String,String> params);
}
