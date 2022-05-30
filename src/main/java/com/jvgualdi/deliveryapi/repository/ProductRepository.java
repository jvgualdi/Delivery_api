package com.jvgualdi.deliveryapi.repository;

import com.jvgualdi.deliveryapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
