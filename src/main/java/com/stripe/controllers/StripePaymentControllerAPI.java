package com.stripe.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.repositories.CustomerRepo;
import com.stripe.request.PaymentDataRequest;
import com.stripe.request.PriceDataRequest;
import com.stripe.services.PriceService;
import com.stripe.services.ProductService;
import com.stripe.services.StripePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StripePaymentControllerAPI {

    @Autowired
    StripePaymentService paymentService;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ProductService productService;
    @Autowired
    PriceService priceService;


    @Value("${stripe.apikey}")
    String stripeKey;

   @RequestMapping("/create_customer")
    public CustomerData createCustomer(@RequestBody CustomerData customerData) throws StripeException {
        Stripe.apiKey = stripeKey;
        CustomerCreateParams params1 =
                CustomerCreateParams.builder()
                        .setName(customerData.getName())
                        .setEmail(customerData.getEmail())
                        .build();
        Customer customer = Customer.create(params1);
        customerData.setCustomerStripeId(customer.getId());
        return paymentService.createCustomer(customerData);
    }

    @RequestMapping("/create_payment")
    public PaymentData createPaymentMethod (@RequestBody PaymentDataRequest paymentData) throws StripeException {
        Stripe.apiKey = stripeKey;

        String customerId = null;
        CustomerData customerData = customerRepo.findByEmail(paymentData.getEmail());
//        PaymentData paymentData1 = null;
        PaymentData paymentData1 = null;
        if (customerData != null) {
            customerId = customerData.getCustomerStripeId();

            // }

            PaymentIntentCreateParams params2 =
                    PaymentIntentCreateParams.builder()
                            .setAmount(paymentData.getAmount())
                            .setCurrency(paymentData.getCurrency())
                            .setPaymentMethod("pm_card_us")
                            .setCustomer(customerId)
                            .build();


            PaymentIntent paymentIntent = PaymentIntent.create(params2);
            System.out.println(paymentIntent);


            paymentData1 = new PaymentData();
            paymentData1.setAmount(paymentIntent.getAmount());
            paymentData1.setCurrency(paymentIntent.getCurrency());
            //paymentData1.setName(paymentIntent.getCustomer());
            // paymentData1.setCustomer(paymentIntent.getCustomer());
            paymentData1.setCustomerStripeId(paymentIntent.getCustomer());
            System.out.println(paymentData1);
            return paymentService.createPaymentMethod(paymentData1);

        } else {
            System.out.println("customer not exist");
        }

        //      return paymentData;
        return paymentService.createPaymentMethod(paymentData1);
    }
        /*else {
            System.out.println();
        }*/
@RequestMapping("/create_product")
 public ProductData createProduct(@RequestBody ProductData productData) throws StripeException
{
     Stripe.apiKey = stripeKey;

     return productService.createProduct(productData);
 }
@RequestMapping("/createPrice")
public  PriceData createPrice(@RequestBody PriceDataRequest priceData) throws StripeException {
    Stripe.apiKey=stripeKey;

    return priceService.createPrice(priceData);
}

}
