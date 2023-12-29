package com.example.myrh.controller;


import com.example.myrh.dto.requests.JobSeekerReq;
import com.example.myrh.dto.responses.JobSeekerRes;

import com.example.myrh.service.IJobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("myrh/api/v1/jobSeekers")
@CrossOrigin("*")
public class JobSeekerController {
    private final IJobSeekerService service;

    @Autowired
    public JobSeekerController(IJobSeekerService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<JobSeekerRes> save(@RequestBody JobSeekerReq req) {
        JobSeekerRes response = service.create(req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<JobSeekerRes>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<JobSeekerRes> get(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }
}
