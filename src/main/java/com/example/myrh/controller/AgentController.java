package com.example.myrh.controller;

import com.example.myrh.dto.requests.AgentReq;
import com.example.myrh.dto.responses.AgentRes;
import com.example.myrh.dto.responses.CompanyRes;
import com.example.myrh.service.IAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("myrh/api/v1/agents")
@CrossOrigin("*")
public class AgentController {

    private final IAgentService service;

    @Autowired
    public AgentController(IAgentService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<AgentRes> save(@RequestBody AgentReq agentReq) {
        AgentRes response = service.create(agentReq);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<AgentRes>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<AgentRes> get(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

}
