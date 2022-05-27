package com.jvgualdi.deliveryapi.service;


import com.jvgualdi.deliveryapi.dto.DeliveryDTO;
import com.jvgualdi.deliveryapi.model.Delivery;
import com.jvgualdi.deliveryapi.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryService {

    @Autowired
    public DeliveryRepository deliveryRepository;

    public void save(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setTax(deliveryDTO.getTax());
        delivery.setTimeDelivered(LocalDateTime.now());

        deliveryRepository.save(delivery);
    }


//    public void delete(Integer deliveryID) {
//        deliveryRepository.delete(deliveryRepository.findById(deliveryID).orElse(null));
//    }
}
