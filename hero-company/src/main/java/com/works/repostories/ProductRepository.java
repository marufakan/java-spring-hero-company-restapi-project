package com.works.repostories;

import com.works.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryIdEquals(int categoryId);

    List<Product> findByNameLikeIgnoreCase(String name);

    Optional<Product> findByPidAndStockGreaterThanEqual(Long pid, int stock);

    //todo:ordera eklemek istediğinde stock güncelenecek
    @Modifying
    @Query(value = "UPDATE product SET stock=?1  WHERE pid=?2 ", nativeQuery = true)
    void updateStock(int stock, Long id);

}