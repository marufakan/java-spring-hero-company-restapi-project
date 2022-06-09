package com.works.repostories;

import com.works.entities.JWTAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JWTAdminRepository extends JpaRepository<JWTAdmin, Long> {
    Optional<JWTAdmin> findByEmailEqualsIgnoreCase(String email);

    @Modifying
    @Query(value = "UPDATE jwtadmin SET company_name=?1, first_name=?2,last_name=?3,email=?4 WHERE id=?5 ", nativeQuery = true)
    void settingsAdmin(String company_name, String first_name, String last_name, String email,Long id);

    @Modifying
    @Query(value = "UPDATE jwtadmin SET password=?1  WHERE id=?2 ", nativeQuery = true)
    void changePassword(String password, Long id);

}