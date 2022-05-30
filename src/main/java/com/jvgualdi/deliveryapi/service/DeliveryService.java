package com.jvgualdi.deliveryapi.service;


import com.jvgualdi.deliveryapi.dto.DeliveryDTO;
import com.jvgualdi.deliveryapi.model.Delivery;
import com.jvgualdi.deliveryapi.model.DeliveryStatus;
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

    public void save(DeliveryDTO deliveryDTO) {
        Delivery delivery = new Delivery();
        delivery.setTax(deliveryDTO.getTax());
        delivery.setDeliveryStatus(deliveryDTO.getDeliveryStatus());

        ProductOrder order = productOrderRepository.findById(deliveryDTO.getProductOrder().getId()).get();
        order.setTotal(order.getSubTotal() + delivery.getTax());
        delivery.setProductOrder(order);

        if (delivery.getDeliveryStatus() == DeliveryStatus.DELIVERED){
            delivery.setTimeDelivered(LocalDateTime.now());
            order.setStatus(OrderStatus.CONCLUDED);
            productOrderRepository.save(order);
        }

        deliveryRepository.save(delivery);
    }

//    public void save(DeliveryDTO deliveryDTO, Integer order_ID) {
//        Delivery delivery = new Delivery();
//        delivery.setTax(deliveryDTO.getTax());
//        delivery.setDeliveryStatus(deliveryDTO.getDeliveryStatus());
//
//        ProductOrder order = productOrderRepository.findById(order_ID).orElse(null);
//        order.setTotal(order.getSubTotal() + delivery.getTax());
//        delivery.setProductOrder(order);
//
//        if (delivery.getDeliveryStatus() == DeliveryStatus.DELIVERED){
//            delivery.setTimeDelivered(LocalDateTime.now());
//            order.setStatus(OrderStatus.CONCLUDED);
//            productOrderRepository.save(order);
//        }
//
//        deliveryRepository.save(delivery);
//    }

    public void update(Delivery delivery, DeliveryDTO deliveryDTO) {
        ProductOrder productOrder = productOrderRepository.findById( delivery.getProductOrder().getId()).get();

        if (delivery.getTax() != deliveryDTO.getTax() && productOrder != null){
            delivery.setTax(deliveryDTO.getTax());
            productOrder.setTotal(productOrder.getSubTotal() + deliveryDTO.getTax());
        }

        if (deliveryDTO.getDeliveryStatus() == DeliveryStatus.DELIVERED && delivery.getDeliveryStatus() != DeliveryStatus.DELIVERED){
            delivery.setTimeDelivered(LocalDateTime.now());
            delivery.setDeliveryStatus(deliveryDTO.getDeliveryStatus());

            productOrder.setStatus(OrderStatus.CONCLUDED);
        }
        productOrderRepository.save(productOrder);
        deliveryRepository.save(delivery);
    }

}
