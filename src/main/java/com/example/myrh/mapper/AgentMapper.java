package com.example.myrh.mapper;

import com.example.myrh.dto.requests.AgentReq;
import com.example.myrh.dto.responses.AgentRes;
import com.example.myrh.model.Agent;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgentMapper implements IMapper<Agent, AgentReq, AgentRes>{

    private final ModelMapper modelMapper;

    @Autowired
    public AgentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AgentRes toRes(Agent agent) {
        return this.modelMapper.map(agent, AgentRes.class);
    }

    @Override
    public AgentReq toReq(Agent agent) {
        return this.modelMapper.map(agent, AgentReq.class);
    }

    @Override
    public Agent resToEntity(AgentRes agentRes) {
        return this.modelMapper.map(agentRes, Agent.class);
    }

    @Override
    public Agent reqToEntity(AgentReq agentReq) {
        return this.modelMapper.map(agentReq, Agent.class);
    }
}
