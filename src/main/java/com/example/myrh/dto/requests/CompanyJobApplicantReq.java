package com.example.myrh.dto.requests;

import com.example.myrh.enums.JobApplicationStatus;
import lombok.Data;

@Data
public class CompanyJobApplicantReq {

    private int companyId;
    private int offerId;
    private int jobSeekerId;
    private JobApplicationStatus status;
}
