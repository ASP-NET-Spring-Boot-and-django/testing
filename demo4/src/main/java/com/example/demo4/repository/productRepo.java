package com.example.demo4.repository;


import com.example.demo4.model.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepo extends JpaRepository<product,Long> {
    List<product> findByCustomerId(Long customerId);
}
