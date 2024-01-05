package com.example.myrh.dto.requests;

import com.example.myrh.enums.OfferStatus;
import com.example.myrh.enums.StudyLevel;
import com.example.myrh.model.ActivityArea;
import com.example.myrh.model.City;
import com.example.myrh.model.Company;
import com.example.myrh.model.Recruiter;
import lombok.Data;

@Data
public class OfferReq {
    private String title;
    private String description;
    private Company company;
    private ActivityArea profile;
    private City city;
    private StudyLevel level;
    private OfferStatus status = OfferStatus.PENDING;
    private float salary;
}
