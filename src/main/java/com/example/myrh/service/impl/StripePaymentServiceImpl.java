package com.example.myrh.service.impl;

import com.example.myrh.model.StripeHistory;
import com.example.myrh.repository.StripHistoryRepository;
import com.example.myrh.service.FakeStripeChargeService;
import com.example.myrh.service.PaymentService;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service

public class StripePaymentServiceImpl implements PaymentService {


    @Value("${stripe.api.key}")
    private String secretKey;
    private FakeStripeChargeService fakeStripeChargeService;


    public StripePaymentServiceImpl(FakeStripeChargeService fakeStripeChargeService) {

        this.fakeStripeChargeService = fakeStripeChargeService;
    }

    @Override
    public boolean isCompanyPaymentValid(String companyId) {
        return false;
    }

    @Override
    public boolean pay(String token, double amount) throws StripeException {
        Stripe.apiKey = secretKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "MAD");
        params.put("source", token);

//        Charge charge = Charge.create(params);
        StripeHistory stripeHistory = this.fakeStripeChargeService.pay(params);

        return true;
    }



    @Override
    public boolean cancel(String companyId) {
        return false;
    }
}


