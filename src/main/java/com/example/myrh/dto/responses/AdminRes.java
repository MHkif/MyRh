package com.example.myrh.dto.responses;

import lombok.Data;

@Data
public class AdminRes {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String image;
}
