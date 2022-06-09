package com.works.repostories;

import com.works.entities.JWTCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JWTCustomerRepository extends JpaRepository<JWTCustomer, Long> {
    Optional<JWTCustomer> findByEmailEqualsIgnoreCase(String email);

    @Modifying
    @Query(value = "UPDATE jwtcustomer SET first_name=?1, last_name=?2,email=?3, phone=?4 WHERE id=?5 ", nativeQuery = true)
    void settingsCustomer(String first_name, String last_name,String email,String phone ,Long id);


}