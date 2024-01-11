package com.example.myrh.service;

import com.example.myrh.model.StripeHistory;

import java.util.Map;

public interface FakeStripeChargeService {
    public StripeHistory pay(Map<String, Object> params);
}
