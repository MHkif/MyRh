package com.example.myrh.controller;

import com.example.myrh.dto.HttpRes;
import com.example.myrh.dto.requests.CompanyReq;
import com.example.myrh.dto.responses.CompanyRes;
import com.example.myrh.service.ICompanyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/companies")
@CrossOrigin("*")
public class CompanyController {

    private final ICompanyService service;

    public CompanyController(ICompanyService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<HttpRes> save(@Valid @RequestBody CompanyReq req){
       CompanyRes response = service.create(req);
       return ResponseEntity.created(URI.create("")).body(
              HttpRes.builder()
                      .timeStamp(LocalDateTime.now().toString())
                      .statusCode(HttpStatus.CREATED.value())
                      .path("myrh/api/v1/companies")
                      .status(HttpStatus.CREATED)
                      .message("New Company has been created")
                      .developerMessage("Company record has been added to database")
                      .data(Map.of("response", response))
                      .build()
       );
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyRes>  update(@RequestBody CompanyReq req, @PathVariable Integer id) {
        return ResponseEntity.ok(new CompanyRes());
    }
    @GetMapping("")
    public ResponseEntity<Page<CompanyRes>> getAll(@RequestParam int page, @RequestParam int size){
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<CompanyRes> get(@PathVariable int id){
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("{id}")
    void deleteEmployee(@PathVariable Long id) {

    }
}
