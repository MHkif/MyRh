package com.example.myrh.dto.requests;

import com.example.myrh.model.Company;
import lombok.Data;

@Data
public class JobSeekerReq {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String image;

}
