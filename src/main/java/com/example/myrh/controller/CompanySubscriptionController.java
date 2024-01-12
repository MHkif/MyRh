package com.example.myrh.controller;

import com.example.myrh.dto.CompanySubscribeRequest;
import com.example.myrh.dto.HttpRes;
import com.example.myrh.enums.SubscriptionStatus;
import com.example.myrh.service.CompanySubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/company/subscriptions")
@CrossOrigin("*")
public class CompanySubscriptionController {
    private final CompanySubscriptionService companySubscriptionService;

    public CompanySubscriptionController(CompanySubscriptionService companySubscriptionService) {
        this.companySubscriptionService = companySubscriptionService;
    }

//    ResponseEntity.created(URI.create("")).body(
//            HttpRes.builder()
//                        .timeStamp(LocalDateTime.now().toString())
//            .statusCode(HttpStatus.CREATED.value())
//            .path("myrh/api/v1/jobSeekers")
//                        .status(HttpStatus.CREATED)
//                        .message("JobSeeker has been Created")
//                        .developerMessage("JobSeeker  has been Created")
//                        .data(Map.of("response", response))
//            .build()

    @PostMapping("/subscribe")
    public ResponseEntity<HttpRes> subscribe(@RequestBody CompanySubscribeRequest request) {
        boolean isSubscribed  = companySubscriptionService.subscribe(request.getCompanyId(), request.getSubscriptionStatus(), request.getToken());
        if (!isSubscribed){
            return ResponseEntity.badRequest().body(
                    HttpRes.builder()
                            .timeStamp(LocalDateTime.now().toString())
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .path("myrh/api/v1/company/subscriptions/subscribe")
                            .status(HttpStatus.BAD_REQUEST)
                            .message("Subscription failed")
                            .developerMessage("Subscription failed")
                            .data(Map.of("response", "Subscription failed"))
                            .build()
            );
        }
        return ResponseEntity.ok(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.OK.value())
                        .path("myrh/api/v1/company/subscriptions/subscribe")
                        .status(HttpStatus.OK)
                        .message("Subscription successful")
                        .developerMessage("Subscription successful")
                        .data(Map.of("response", "Subscription successful"))
                        .build());
    }

}
