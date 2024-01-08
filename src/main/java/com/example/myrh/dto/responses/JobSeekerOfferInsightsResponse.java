package com.example.myrh.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JobSeekerOfferInsightsResponse {
    int candidateId;
    int nbCandidatesAccepted;
    int nbCandidatesRefused;
    int nbCandidatesWaiting;
    int nbCandidatesInProcess;
    Collection<CandidateOffersApply> candidateOffersApplyCollection = new ArrayList<>();
}
