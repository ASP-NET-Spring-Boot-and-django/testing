package com.example.demo4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String productName;
    int productQuantity;
    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading for the customer relationship
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public com.example.demo4.model.customer getCustomer() {
        return customer;
    }

    public void setCustomer(com.example.demo4.model.customer customer) {
        this.customer = customer;
    }
}
