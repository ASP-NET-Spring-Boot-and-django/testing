package com.example.demo4.repository;

import com.example.demo4.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepo extends JpaRepository<customer,Long> {
    customer findByName(String name);
}
