package com.stripe.services;

import com.stripe.model.CustomerData;
import com.stripe.model.PaymentData;
import com.stripe.repositories.CustomerRepo;
import com.stripe.repositories.ProductRepo;
import com.stripe.repositories.StripePaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentService {

    @Autowired
    StripePaymentRepo paymentRepo;

    @Autowired
    CustomerRepo customerRepo;
   @Autowired
    ProductRepo productRepo;

    public PaymentData createPaymentMethod(PaymentData paymentData1)
    {

        return paymentRepo.save(paymentData1);
    }

    public CustomerData createCustomer(CustomerData customerData) {
        return customerRepo.save(customerData);
    }

//    public Optional<CustomerData> getCustomer(String email) {
//        return customerRepo.findById(email);
//    }



}
