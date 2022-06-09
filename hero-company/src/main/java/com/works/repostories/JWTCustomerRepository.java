package com.works.repostories;

import com.works.entities.JWTCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JWTCustomerRepository extends JpaRepository<JWTCustomer, Long> {
    Optional<JWTCustomer> findByEmailEqualsIgnoreCase(String email);
}