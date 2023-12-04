package com.stripe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class PriceData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String currency;
    Long unitAmount;
    String productId;
    String name;
}
