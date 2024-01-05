package com.example.myrh.controller;

import com.example.myrh.dto.FileModel;
import com.example.myrh.dto.requests.JobApplicantReq;
import com.example.myrh.dto.requests.JobSeekerReq;
import com.example.myrh.dto.responses.JobApplicantRes;
import com.example.myrh.dto.responses.JobSeekerRes;
import com.example.myrh.model.JobApplicantId;
import com.example.myrh.service.IJobApplicantService;
import com.example.myrh.service.impl.CloudinaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("myrh/api/v1/jobApplicants")
@CrossOrigin("*")
@RequiredArgsConstructor
public class JobApplicantController {

    private final IJobApplicantService service;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JobApplicantRes> save(@ModelAttribute @Valid JobApplicantReq req) {
        JobApplicantRes response = service.create(req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<JobApplicantRes>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("{offerId}/{jobSeekerId}")
    public ResponseEntity<JobApplicantRes> get(@PathVariable int offerId, @PathVariable int jobSeekerId) {
        JobApplicantId id = new JobApplicantId();
        id.setOffer_id(offerId);
        id.setJobSeeker_id(jobSeekerId);
        return ResponseEntity.ok(service.getById(id));
    }
}
