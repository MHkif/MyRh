package com.example.myrh.dto.responses;

import com.example.myrh.enums.JobSeekerStatus;
import com.example.myrh.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JobSeekerOfferInsightsResponse {
    @JsonProperty("jobSeeker_id")
    int candidateId;
    @JsonProperty("jobSeeker_name")
    String jobSeekerName;
    @JsonProperty("nb_job_accepted")
    int nbCandidatesAccepted;
    @JsonProperty("nb_job_refused")
    int nbCandidatesRefused;
    @JsonProperty("nb_job_waiting")
    int nbCandidatesWaiting;
    @JsonProperty("nb_job_in_process")
    int nbCandidatesInProcess;
    UserStatus jobSeeker_status ;
    Collection<CandidateOffersApply> candidateOffersApplyCollection = new ArrayList<>();
}
