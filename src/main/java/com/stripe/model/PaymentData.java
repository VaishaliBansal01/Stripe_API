package com.stripe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class PaymentData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int customerId;
    public String cardNo;
    public Long expMonth;
    public Long expYear;
    public String cvc;
   public String name;
    public String email;
   //public Object customer;

    Long amount;
    String currency;
    public String customerStripeId;

  /*  @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }*/
}
