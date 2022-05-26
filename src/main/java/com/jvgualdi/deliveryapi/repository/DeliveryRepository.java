package com.jvgualdi.deliveryapi.repository;

import com.jvgualdi.deliveryapi.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {



}
