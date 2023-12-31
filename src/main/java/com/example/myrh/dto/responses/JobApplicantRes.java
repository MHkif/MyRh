package com.example.myrh.dto.responses;

import com.example.myrh.dto.requests.JobSeekerReq;
import com.example.myrh.model.JobApplicantId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobApplicantRes {
    private JobApplicantId id;
    private LocalDateTime createdDate = LocalDateTime.now();
    private JobSeekerRes jobSeeker;
    private String resume;
    private Boolean isViewed;
}
