package com.example.myrh.service.impl;

import com.example.myrh.model.StripeHistory;
import com.example.myrh.repository.StripHistoryRepository;
import com.example.myrh.service.FakeStripeChargeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FakeStripeChargeServiceImpl implements FakeStripeChargeService {

    private  final StripHistoryRepository stripHistoryRepository;

    public FakeStripeChargeServiceImpl(StripHistoryRepository stripHistoryRepository) {
        this.stripHistoryRepository = stripHistoryRepository;
    }
    @Override
    public StripeHistory pay(Map<String, Object> params){
        StripeHistory stripeHistory = new StripeHistory();
        stripeHistory.setAmount((double) params.get("amount"));
        stripeHistory.setCompanyId((String) params.get("source"));
        stripeHistory.setCurrency((String) params.get("currency"));
        stripeHistory.setToken((String) params.get("source"));
        stripeHistory.setCreatedAt(java.time.LocalDateTime.now());
        return stripHistoryRepository.save(stripeHistory);
    };
}