package com.example.myrh.service;

import com.example.myrh.dto.requests.AdminReq;
import com.example.myrh.dto.responses.AdminRes;
import com.example.myrh.model.Admin;

public interface IAdminService extends IService<Admin, Integer, AdminReq, AdminRes>{
    AdminRes auth(String email, String password);
}
