package com.example.myrh.controller;

import com.example.myrh.dto.requests.OfferReq;
import com.example.myrh.dto.responses.CompanyRes;
import com.example.myrh.dto.responses.OfferRes;
import com.example.myrh.enums.StudyLevel;
import com.example.myrh.service.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("myrh/api/v1/offers")
@CrossOrigin("*")
public class OfferController {
    private final IOfferService service;

    @Autowired
    public OfferController(IOfferService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<OfferRes> save(@RequestBody OfferReq req) {
        OfferRes response = service.create(req);
        return ResponseEntity.ok(response);
    }

/*
    @GetMapping("")
    public ResponseEntity<Page<OfferRes>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }


 */
    @GetMapping("")
    public ResponseEntity<Page<OfferRes>>
    search(@RequestParam(required = false , defaultValue = "0") int page , @RequestParam(required = false,defaultValue = "10") int size,@RequestParam(required = false) String title, @RequestParam(required = false) String description, @RequestParam(required = false) String domain,@RequestParam(required = false) String city,@RequestParam(required = false) StudyLevel level,@RequestParam(required = false) String job) {
        return ResponseEntity.ok(service.
                search(page, size, title, description, domain, city, level, job)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<OfferRes> get(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<OfferRes> update(@PathVariable int id, @RequestBody OfferRes offer) {
        OfferRes response = this.service.update(id, offer);
        return ResponseEntity.ok(response);
    }

}
