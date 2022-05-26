package com.jvgualdi.deliveryapi.service;

import com.jvgualdi.deliveryapi.dto.ProductOrderDTO;
import com.jvgualdi.deliveryapi.model.ProductOrder;
import com.jvgualdi.deliveryapi.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductOrderService {

    @Autowired
    public ProductOrderRepository productOrderRepository;

    public void save(ProductOrderDTO productOrderDTO) {
        ProductOrder productOrder = new ProductOrder();

        productOrder.setStatus(productOrderDTO.getStatus());
        productOrder.setDescription(productOrderDTO.getDescription());
        productOrder.setSubTotal(productOrderDTO.getSubTotal());
        productOrder.setTotal(productOrderDTO.getTotal());
        productOrder.setMomentRequested(LocalDateTime.now());

        productOrderRepository.save(productOrder);
    }
}
