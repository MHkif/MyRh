package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.AdminReq;
import com.example.myrh.dto.responses.AdminRes;
import com.example.myrh.exception.BadRequestException;
import com.example.myrh.mapper.AdminMapper;
import com.example.myrh.mapper.CompanyMapper;
import com.example.myrh.model.Admin;
import com.example.myrh.repository.AdminRepo;
import com.example.myrh.repository.CompanyRepo;
import com.example.myrh.service.IAdminService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminServiceImpl implements IAdminService {
    private final AdminRepo repository;
    private final CompanyRepo companyRepo;
    private final AdminMapper mapper;
    private final CompanyMapper companyMapper;

    @Autowired
    public AdminServiceImpl(AdminRepo repository, CompanyRepo companyRepo, AdminMapper mapper, CompanyMapper companyMapper) {
        this.repository = repository;
        this.companyRepo = companyRepo;
        this.mapper = mapper;
        this.companyMapper = companyMapper;
    }

    @Override
    public AdminRes getById(Integer id) {
        Admin agent = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Admin not found"));
        return mapper.toRes(agent);
    }

    @Override
    public Page<AdminRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public AdminRes create(AdminReq request) {
        if(repository.existsByEmail(request.getEmail())){
            throw new BadRequestException("Email Already Taken");
        }
        return mapper.toRes(repository.save(mapper.reqToEntity(request)));
    }

    @Override
    public AdminRes update(Integer id, AdminRes res) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public AdminRes auth(String email, String password) {
        if (repository.existsByEmail(email)) {
            Admin admin = repository.findByEmail(email);
            if (!Objects.equals(admin.getPassword(), password)) {
                throw new BadRequestException("Incorrect Passowrd");
            }
            return  mapper.toRes(admin);

        } else {
            throw new EntityNotFoundException("No Admin Found with this Email");
        }
    }
}
