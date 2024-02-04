package com.example.myrh.controller;

import com.example.myrh.dto.HttpRes;
import com.example.myrh.dto.requests.AdminReq;
import com.example.myrh.dto.requests.AuthReq;
import com.example.myrh.dto.responses.AdminRes;
import com.example.myrh.service.IAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/admin")
@CrossOrigin("*")
public class AdminController {

    private final IAdminService service;

    @Autowired
    public AdminController(IAdminService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<AdminRes> save(@RequestBody AdminReq agentReq) {
        AdminRes response = service.create(agentReq);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth")
    public ResponseEntity<HttpRes> auth(@Valid @RequestBody AuthReq auth){
        AdminRes response = service.auth(auth.getEmail(), auth.getPassword());
        return ResponseEntity.accepted().body(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .path("myrh/api/v1/admin")
                        .status(HttpStatus.ACCEPTED)
                        .message("Admin has been authenticated")
                        .developerMessage("Admin  has been authenticated")
                        .data(Map.of("response", response))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<Page<AdminRes>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminRes> get(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

}
