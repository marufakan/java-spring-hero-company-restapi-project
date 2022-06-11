package com.works.repostories;

import com.works.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findByCreatedByEqualsIgnoreCaseAndStatusEquals(String createdBy, int status);

}