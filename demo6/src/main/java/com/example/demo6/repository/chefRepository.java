package com.example.demo6.repository;

import com.example.demo6.model.Chef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface chefRepository extends JpaRepository<Chef,Long> {
 Chef findByChefName(String name);
}
