package com.stripe.repositories;

import com.stripe.model.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductData, Long> {

    ProductData findByName(String name);
}
