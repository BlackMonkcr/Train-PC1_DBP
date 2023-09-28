package com.example.trainpc1_dbp.infrastructure;

import com.example.trainpc1_dbp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.trainpc1_dbp.domain.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Optional<Product> findByNameAndSeller(String name, User seller);
}