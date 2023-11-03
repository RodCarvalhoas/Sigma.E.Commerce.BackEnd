package com.Sigma.SigmaBackEnd.repository;

import com.Sigma.SigmaBackEnd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT obj FROM Product obj WHERE obj.category.name = :categoryName ORDER BY obj.name")
    List<Product> findAllByCategory(@Param(value = "categoryName") String categoryName);

}
