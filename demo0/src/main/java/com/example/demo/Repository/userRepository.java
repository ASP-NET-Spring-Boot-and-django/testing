package com.example.demo.Repository;

import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<Users,Long> {
    Users findByName(String name);
}
