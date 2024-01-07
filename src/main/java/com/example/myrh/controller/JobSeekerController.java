package com.example.myrh.controller;


import com.example.myrh.dto.requests.JobSeekerReq;
import com.example.myrh.dto.responses.JobSeekerRes;

import com.example.myrh.service.IJobSeekerFilterService;
import com.example.myrh.service.IJobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/jobSeekers")
@CrossOrigin("*")
public class JobSeekerController {
    private final IJobSeekerService service;
    private final IJobSeekerFilterService jobSeekerFilterService;

    @Autowired
    public JobSeekerController(IJobSeekerService service, IJobSeekerFilterService jobSeekerFilterService) {
        this.service = service;
        this.jobSeekerFilterService = jobSeekerFilterService;
    }

    @PostMapping("")
    public ResponseEntity<JobSeekerRes> save(@RequestBody JobSeekerReq req) {
        JobSeekerRes response = service.create(req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<JobSeekerRes>> getAll(@RequestParam int page, @RequestParam int size ) {
        return ResponseEntity.ok(service.getAll(page, size));
    }
    // TODO : 7-01-2024 Avoir tous les candidats filtré par type de candidature (Online ou Offline) ou par titre de l'offre
    @GetMapping("/filter")
    public ResponseEntity<Page<JobSeekerRes>> filterAll(
            @RequestParam  Map<String,String> params) {
        return ResponseEntity.ok(jobSeekerFilterService.filterAll(params));
    }

    @GetMapping("{id}")
    public ResponseEntity<JobSeekerRes> get(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
        
    }
    


    
    
}
