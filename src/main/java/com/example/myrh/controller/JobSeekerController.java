package com.example.myrh.controller;


import com.example.myrh.dto.HttpRes;
import com.example.myrh.dto.requests.AuthReq;
import com.example.myrh.dto.requests.JobSeekerReq;
import com.example.myrh.dto.responses.CompanyRes;
import com.example.myrh.dto.responses.JobSeekerRes;

import com.example.myrh.service.IJobSeekerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

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
    public ResponseEntity<HttpRes> save(@Valid @RequestBody JobSeekerReq req) {
        JobSeekerRes response = service.create(req);
          return ResponseEntity.created(URI.create("")).body(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.CREATED.value())
                        .path("myrh/api/v1/jobSeekers")
                        .status(HttpStatus.CREATED)
                        .message("JobSeeker has been Created")
                        .developerMessage("JobSeeker  has been Created")
                        .data(Map.of("response", response))
                        .build()
        );
    }

    @PostMapping("/auth")
    public ResponseEntity<HttpRes> auth(@Valid @RequestBody AuthReq auth){
        JobSeekerRes response = service.auth(auth.getEmail(), auth.getPassword());
        return ResponseEntity.accepted().body(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .path("myrh/api/v1/jobSeekers")
                        .status(HttpStatus.ACCEPTED)
                        .message("JobSeeker has been authenticated")
                        .developerMessage("JobSeeker  has been authenticated")
                        .data(Map.of("response", response))
                        .build()
        );
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
