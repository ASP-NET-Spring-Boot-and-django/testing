package com.example.demo5.repository;

import com.example.demo5.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface chefRepository extends JpaRepository<Chef,Long> {
    Chef findByChefname(String name);
}
