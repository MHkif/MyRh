package com.example.myrh.dto;

import com.example.myrh.enums.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanySubscribeRequest {
    String companyId;
    SubscriptionStatus subscriptionStatus;
    String token;
}
