package com.example.myrh.dto.responses;

import com.example.myrh.enums.OfferStatus;
import com.example.myrh.enums.StudyLevel;
import lombok.Data;

@Data
public class OfferRes {

    private int id;
    private String title;
    private String description;
    private CompanyRes company;
    private ActivityAreaRes profile;
    private CityRes city;
    private StudyLevel level;
    private OfferStatus status;
    private float salary;


}
