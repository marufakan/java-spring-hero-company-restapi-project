package com.works.repostories;

import com.works.entities.JWTAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JWTAdminRepository extends JpaRepository<JWTAdmin, Long> {
    Optional<JWTAdmin> findByEmailEqualsIgnoreCase(String email);
}