package com.stripe.repositories;

import com.stripe.model.PriceData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepo extends JpaRepository<PriceData, Long> {
}
