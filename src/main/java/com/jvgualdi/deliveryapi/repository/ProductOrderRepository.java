package com.jvgualdi.deliveryapi.repository;

import com.jvgualdi.deliveryapi.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {

    List<ProductOrder> findByCustomer(Integer customerID);

}
