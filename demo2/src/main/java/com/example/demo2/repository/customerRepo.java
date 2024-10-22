package com.example.demo2.repository;

import com.example.demo2.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface customerRepo extends JpaRepository<customer,Long> {

    customer findByName(String name);
}
