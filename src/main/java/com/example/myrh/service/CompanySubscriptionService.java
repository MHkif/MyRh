package com.example.myrh.service;

import com.example.myrh.enums.SubscriptionStatus;
import com.example.myrh.model.Company;

public interface CompanySubscriptionService {

    SubscriptionStatus getSubscriptionStatus(String companyId);
    boolean subscribe(String companyId, SubscriptionStatus subscriptionStatus , String token);

    boolean unsubscribe(String companyId);

}
