package com.stripe.request;

import lombok.Data;
@Data
public class PaymentDataRequest {

    Long amount;
    String currency;
    String email;

  /*  @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }*/
}
