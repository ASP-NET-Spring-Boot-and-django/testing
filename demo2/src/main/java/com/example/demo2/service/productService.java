package com.example.demo2.service;

import com.example.demo2.model.product;
import com.example.demo2.repository.productRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    productRepo productrepo;
    productService(productRepo productrepo){
        this.productrepo=productrepo;
    }

    public List<product> getAllproducts() {

    return productrepo.findAll();
    }



    public product addAProduct(product prod) {
    return productrepo.save(prod);
    }

}
