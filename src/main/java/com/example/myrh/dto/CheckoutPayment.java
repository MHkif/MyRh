package com.example.myrh.dto;

import com.example.myrh.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CheckoutPayment {

	private String name;
	private SubscriptionStatus subscriptionStatus;
	private String currency;
	private String successUrl;
	private String cancelUrl;
	private long amount;
	private long quantity;

}