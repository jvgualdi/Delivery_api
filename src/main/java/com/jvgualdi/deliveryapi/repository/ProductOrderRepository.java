package com.jvgualdi.deliveryapi.repository;

import com.jvgualdi.deliveryapi.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {


}
