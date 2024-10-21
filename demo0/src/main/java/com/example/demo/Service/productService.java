package com.example.demo.Service;

import com.example.demo.Repository.productRepository;
import com.example.demo.model.product;
import jakarta.servlet.http.HttpServlet;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class productService {

    productRepository productrepo;

    productService(productRepository productrepo){
        this.productrepo=productrepo;
    }

    public List<product> getAllProducts(){
        return productrepo.findAll();
    }

    public product addProduct(product product){
        return productrepo.save(product);
    }
}
