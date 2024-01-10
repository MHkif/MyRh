package com.example.myrh.service.impl;

import com.example.myrh.dto.requests.ActivityAreaReq;
import com.example.myrh.dto.responses.ActivityAreaRes;
import com.example.myrh.mapper.ActivityAreaMapper;
import com.example.myrh.model.ActivityArea;
import com.example.myrh.repository.ActivityAreaRepo;
import com.example.myrh.service.IActivityAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ActivityAreaServiceImpl implements IActivityAreaService {

    private final ActivityAreaRepo repository;
    private final ActivityAreaMapper mapper;

    @Autowired
    public ActivityAreaServiceImpl(ActivityAreaRepo repository, ActivityAreaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ActivityAreaRes getById(Integer id) {
        return null;
    }

    @Override
    public Page<ActivityAreaRes> getAll(int page, int size) {
        return null;
    }

    @Override
    public ActivityAreaRes create(ActivityAreaReq request) {
        ActivityArea profile = repository.save(mapper.reqToEntity(request));
        return mapper.toRes(profile);
    }

    @Override
    public ActivityAreaRes update(Integer id, ActivityAreaRes res) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
