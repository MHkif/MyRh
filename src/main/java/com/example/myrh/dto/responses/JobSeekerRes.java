package com.example.myrh.dto.responses;

import com.example.myrh.model.Offer;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;


@Data
public class JobSeekerRes {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String image;
    private boolean isEnabled;
    //Set<Offer> offers = new HashSet<>();

}
