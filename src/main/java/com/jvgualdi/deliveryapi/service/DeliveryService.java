package com.jvgualdi.deliveryapi.service;


import com.jvgualdi.deliveryapi.dto.DeliveryDTO;
import com.jvgualdi.deliveryapi.model.Delivery;
import com.jvgualdi.deliveryapi.model.OrderStatus;
import com.jvgualdi.deliveryapi.model.ProductOrder;
import com.jvgualdi.deliveryapi.repository.DeliveryRepository;
import com.jvgualdi.deliveryapi.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class DeliveryService {

    @Autowired
    public DeliveryRepository deliveryRepository;

    @Autowired
    public ProductOrderRepository productOrderRepository;

    public void save(DeliveryDTO deliveryDTO, Integer order_ID) {
        Delivery delivery = new Delivery();
        delivery.setTax(deliveryDTO.getTax());
        delivery.setTimeDelivered(LocalDateTime.now());

        ProductOrder order = productOrderRepository.findById(order_ID).orElse(null);
        if (order != null){
            order.setStatus(OrderStatus.DELIVERING);
            productOrderRepository.save(order);

            delivery.setProductOrder(order);
        }
        deliveryRepository.save(delivery);
    }


//    public void delete(Integer deliveryID) {
//        deliveryRepository.delete(deliveryRepository.findById(deliveryID).orElse(null));
//    }
}
