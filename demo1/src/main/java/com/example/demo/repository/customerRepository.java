package com.example.demo.repository;

import com.example.demo.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepository extends JpaRepository<customer,Long> {
    customer findByName(String name);
}
