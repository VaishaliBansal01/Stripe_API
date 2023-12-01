package com.stripe.repositories;

import com.stripe.model.PaymentData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StripePaymentRepo extends JpaRepository<PaymentData, Integer> {
}
