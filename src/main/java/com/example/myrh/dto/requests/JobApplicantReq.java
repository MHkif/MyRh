package com.example.myrh.dto.requests;

import com.example.myrh.enums.JobApplicationStatus;
import com.example.myrh.model.JobApplicantId;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Valid
public class JobApplicantReq {

    private JobApplicantId id;
    private LocalDateTime createdDate = LocalDateTime.now();
    private JobSeekerReq jobSeeker;
    private MultipartFile resume;
    private Boolean isViewed = false;
    private JobApplicationStatus status = JobApplicationStatus.WAITING;
}
