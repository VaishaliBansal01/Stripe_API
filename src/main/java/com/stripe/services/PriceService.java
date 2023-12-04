package com.stripe.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.PriceData;
import com.stripe.model.ProductData;
import com.stripe.param.PriceCreateParams;
import com.stripe.repositories.PriceRepo;
import com.stripe.repositories.ProductRepo;
import com.stripe.request.PriceDataRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PriceService {
    @Autowired
    PriceRepo priceRepo;
    @Autowired
    ProductRepo productRepo;

    public PriceData createPrice(PriceDataRequest priceData) throws StripeException {
         ProductData productData = productRepo.findByName(priceData.getName());
       String productId = null;
         if(productData!= null)
         {
             String productId1 = productData.getProductId();
         }

//        assert productData != null;
        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency(priceData.getCurrency())
                        .setUnitAmount(priceData.getUnitAmount())
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        /*.setProduct(PriceCreateParams.ProductData.builder()
                                        .setName("subscription1")
                                        .build())*/
                        .setProduct()
                        .build();
        Price price = Price.create(params);
        PriceData p1 = new PriceData();
        p1.setCurrency(price.getCurrency());
        p1.setUnitAmount(price.getUnitAmount());
        p1.setProductId(price.getProduct());

        return priceRepo.save(p1);
    }
}