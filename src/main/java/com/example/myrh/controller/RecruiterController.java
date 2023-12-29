package com.example.myrh.controller;

import com.example.myrh.dto.HttpRes;
import com.example.myrh.dto.requests.RecruiterReq;
import com.example.myrh.dto.responses.RecruiterRes;
import com.example.myrh.service.impl.RecruiterServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/recruiters")
@CrossOrigin("*")
public class RecruiterController {

    private final RecruiterServiceImpl service;

    public RecruiterController(RecruiterServiceImpl service) {
        this.service = service;
    }


    @PostMapping("")
    public ResponseEntity<HttpRes> save(@Valid @RequestBody RecruiterReq req){
        RecruiterRes response = service.create(req);
        return ResponseEntity.created(URI.create("")).body(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.CREATED.value())
                        .path("myrh/api/v1/companies")
                        .status(HttpStatus.CREATED)
                        .message("New Recruiter has been created")
                        .developerMessage("Recruiter record has been added to database")
                        .data(Map.of("response", response))
                        .build()
        );
    }
    @GetMapping("")
    public ResponseEntity<Page<RecruiterRes>> getAll(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("confirm-account")
    public ResponseEntity<HttpRes> confirmRecruiterAccount(@RequestParam("token") String token){
        Boolean isSuccess = service.verifyToken(token);
        return ResponseEntity.created(URI.create("")).body(
          HttpRes.builder()
                  .timeStamp(LocalDateTime.now().toString())
                  .status(HttpStatus.OK)
                  .message("Account Verified")
                  .statusCode(HttpStatus.OK.value())
                  .data(Map.of("Success",isSuccess ))
                  .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<RecruiterRes> get(@PathVariable int id){
        return ResponseEntity.ok(service.getById(id));
    }
}
