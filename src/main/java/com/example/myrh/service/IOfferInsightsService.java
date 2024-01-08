package com.example.myrh.service;

import com.example.myrh.dto.responses.JobSeekerOfferInsightsResponse;
import org.springframework.data.domain.Page;

public interface IOfferInsightsService {

    //Avoir des statistiques des offres d'emploi par candidats

    JobSeekerOfferInsightsResponse getCandidatesOfferInsights(int seekerId);
    Page<JobSeekerOfferInsightsResponse> getCandidatesOfferInsights(int page, int size);

    
}
