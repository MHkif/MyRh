package com.example.myrh.mapper;

import com.example.myrh.dto.requests.ActivityAreaReq;
import com.example.myrh.dto.responses.ActivityAreaRes;
import com.example.myrh.model.ActivityArea;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivityAreaMapper implements IMapper<ActivityArea, ActivityAreaReq, ActivityAreaRes> {
    private final ModelMapper modelMapper;

    @Autowired
    public ActivityAreaMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ActivityAreaRes toRes(ActivityArea activityArea) {
        return this.modelMapper.map(activityArea, ActivityAreaRes.class);
    }

    @Override
    public ActivityAreaReq toReq(ActivityArea activityArea) {
        return this.modelMapper.map(activityArea, ActivityAreaReq.class);
    }

    @Override
    public ActivityArea resToEntity(ActivityAreaRes activityAreaRes) {
        return this.modelMapper.map(activityAreaRes, ActivityArea.class);
    }

    @Override
    public ActivityArea reqToEntity(ActivityAreaReq activityAreaReq) {
        return this.modelMapper.map(activityAreaReq, ActivityArea.class);
    }
}
