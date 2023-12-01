package com.stripe.repositories;

import com.stripe.model.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerData, Long> {

    CustomerData findByEmail(String email);
}
