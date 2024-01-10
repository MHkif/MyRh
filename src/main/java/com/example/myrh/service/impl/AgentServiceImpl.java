package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.AgentReq;
import com.example.myrh.dto.responses.AgentRes;
import com.example.myrh.dto.responses.CompanyRes;
import com.example.myrh.mapper.AgentMapper;
import com.example.myrh.mapper.CompanyMapper;
import com.example.myrh.model.Agent;
import com.example.myrh.repository.AgentRepo;
import com.example.myrh.repository.CompanyRepo;
import com.example.myrh.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class AgentServiceImpl implements IAgentService {
    private final AgentRepo repository;
    private final CompanyRepo companyRepo;
    private final AgentMapper mapper;
    private final CompanyMapper companyMapper;

    @Autowired
    public AgentServiceImpl(AgentRepo repository, CompanyRepo companyRepo, AgentMapper mapper, CompanyMapper companyMapper) {
        this.repository = repository;
        this.companyRepo = companyRepo;
        this.mapper = mapper;
        this.companyMapper = companyMapper;
    }

    @Override
    public AgentRes getById(Integer id) {
        Agent agent = repository.findById(id).orElseThrow(() -> new IllegalStateException("Agent not found"));
        return mapper.toRes(agent);
    }

    @Override
    public Page<AgentRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public AgentRes create(AgentReq request) {
        if(repository.existsByEmail(request.getEmail())){
            throw new IllegalStateException("Email Already Taken");
        }
        if(!companyRepo.existsById(request.getCompany().getId())){
            throw new IllegalStateException("Company not found");
        }
        CompanyRes companyRes = companyMapper.toRes(companyRepo.findById(request.getCompany().getId()).get());
        Agent agent = repository.save(mapper.reqToEntity(request));
        AgentRes res = mapper.toRes(agent);
        res.setCompany(companyRes);
        return res;
    }

    @Override
    public AgentRes update(Integer id, AgentRes res) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
