package com.example.myrh.service;

import com.example.myrh.dto.requests.CompanyReq;
import com.example.myrh.dto.responses.CompanyRes;
import com.example.myrh.model.Company;

public interface ICompanyService extends IService<Company, Integer, CompanyReq, CompanyRes>{
    CompanyRes auth(String email, String password);
    Boolean verifyToken(String token) throws Exception;

    Boolean sendVerification(String id) throws Exception;
}
