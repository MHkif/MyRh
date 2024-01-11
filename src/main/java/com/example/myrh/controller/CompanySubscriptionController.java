package com.example.myrh.controller;

import com.example.myrh.dto.CompanySubscribeRequest;
import com.example.myrh.enums.SubscriptionStatus;
import com.example.myrh.service.CompanySubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("myrh/api/v1/company/subscriptions")
public class CompanySubscriptionController {
    private final CompanySubscriptionService companySubscriptionService;

    public CompanySubscriptionController(CompanySubscriptionService companySubscriptionService) {
        this.companySubscriptionService = companySubscriptionService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody CompanySubscribeRequest request) {
        boolean isSubscribed  = companySubscriptionService.subscribe(request.getCompanyId(), request.getSubscriptionStatus(), request.getToken());
        if (!isSubscribed)
            return ResponseEntity.badRequest().body("Subscription failed");
        return ResponseEntity.ok(String.format("Payment successful! Charge ID:  %s", request.getCompanyId(),request.getSubscriptionStatus()));
    }

}
