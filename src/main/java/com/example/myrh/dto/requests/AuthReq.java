package com.example.myrh.dto.requests;

import lombok.Data;

@Data
public class AuthReq {
    private String email;
    private String password;
}
