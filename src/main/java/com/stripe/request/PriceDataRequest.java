package com.stripe.request;

import lombok.Data;

@Data
public class PriceDataRequest {
    String currency;
    Long unitAmount;
  String name;
}
