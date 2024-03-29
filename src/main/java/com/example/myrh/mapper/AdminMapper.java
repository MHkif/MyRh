package com.example.myrh.mapper;

import com.example.myrh.dto.requests.AdminReq;
import com.example.myrh.dto.responses.AdminRes;
import com.example.myrh.model.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper implements IMapper<Admin, AdminReq, AdminRes>{

    private final ModelMapper modelMapper;

    @Autowired
    public AdminMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AdminRes toRes(Admin admin) {
        return this.modelMapper.map(admin, AdminRes.class);
    }

    @Override
    public AdminReq toReq(Admin admin) {
        return this.modelMapper.map(admin, AdminReq.class);
    }

    @Override
    public Admin resToEntity(AdminRes adminRes) {
        return this.modelMapper.map(adminRes, Admin.class);
    }

    @Override
    public Admin reqToEntity(AdminReq adminReq) {
        return this.modelMapper.map(adminReq, Admin.class);
    }
}
