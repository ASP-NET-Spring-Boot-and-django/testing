package com.example.demo4.service;

import com.example.demo4.model.customer;
import com.example.demo4.model.product;
import com.example.demo4.repository.productRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo4.repository.customerRepo;
import java.util.List;

@Service
public class productService {
    @Autowired
    productRepo repo;

    @Autowired
    customerRepo custRepo;
    public List<product> getAllProducts(String username) {
        customer cust=custRepo.findByName(username);

        return repo.findByCustomerId(cust.getId());
    }

    public product createAProduct(product product,String username){
        customer cust=custRepo.findByName(username);
        product.setCustomer(cust);
        return repo.save(product);
    }
}

