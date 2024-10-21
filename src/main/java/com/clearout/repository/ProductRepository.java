package com.clearout.repository;

import com.clearout.entity.Product;
import com.clearout.entity.ProductStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.productStatus <> 'SOLD' and p.individualSeller.id <> :id")
    List<Product> findAllUnsold(Long id);

    @Query("select p from Product p where p.city = :city and p.productStatus <> 'SOLD' and p.individualSeller.id <> :id ")
    List<Product> findUnsoldByCity(String city,Long id);

    @Modifying
    @Transactional
    @Query("update Product p set p.productStatus = :productStatus where p.id = :id")
    void updateProductStatus(Long id, ProductStatus productStatus);

}
