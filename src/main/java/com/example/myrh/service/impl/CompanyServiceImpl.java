package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.CompanyReq;
import com.example.myrh.dto.responses.CompanyRes;
import com.example.myrh.mapper.CompanyMapper;
import com.example.myrh.model.Company;
import com.example.myrh.repository.CompanyRepo;
import com.example.myrh.service.ICompanyService;
import com.example.myrh.exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements ICompanyService {

    private final CompanyRepo repository;
    private final CompanyMapper mapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepo repository, CompanyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public CompanyRes getById(Integer id) {
        Optional<Company> company = repository.findById(id);
        return company.map(mapper::toRes).orElseThrow(() -> new EntityNotFoundException("Comapny Not Exist with the given Id : " + id)
        );
    }

    @Override
    public Page<CompanyRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public CompanyRes create(CompanyReq request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("Email Already Taken");

        }

        Company company = repository.save(mapper.reqToEntity(request));
        return mapper.toRes(company);
    }

    @Override
    public CompanyRes update(int id, CompanyRes res) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
