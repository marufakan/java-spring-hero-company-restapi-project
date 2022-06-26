package com.works.repostories;

import com.works.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByCreatedByEqualsIgnoreCaseAndStatusEquals(String createdBy, int status);

    Optional<Basket> findByCreatedByLikeIgnoreCaseAndStatus(String createdBy, int status);

    Optional<Basket> findByIdIs(Long id);

}