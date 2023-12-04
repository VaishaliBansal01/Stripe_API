package com.stripe.services;

import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductData;
import com.stripe.param.ProductCreateParams;
import com.stripe.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
     public ProductData createProduct(ProductData productData) throws StripeException {

         ProductCreateParams params =
                 ProductCreateParams.builder()
                         .setName(productData.getName())
                         .build();
         Product product = Product.create(params);

         productData.setProductId(product.getId());
         return productRepo.save(productData);
     }



}
