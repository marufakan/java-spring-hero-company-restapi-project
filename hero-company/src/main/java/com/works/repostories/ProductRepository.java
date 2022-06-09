package com.works.repostories;

import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryIdEquals(int categoryId);

    List<Product> findByNameLikeIgnoreCase(String name);

}