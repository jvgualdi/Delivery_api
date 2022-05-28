package com.jvgualdi.deliveryapi.service;

import com.jvgualdi.deliveryapi.dto.ProductOrderDTO;
import com.jvgualdi.deliveryapi.model.Customer;
import com.jvgualdi.deliveryapi.model.ProductOrder;
import com.jvgualdi.deliveryapi.repository.CustomerRepository;
import com.jvgualdi.deliveryapi.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductOrderService {

    @Autowired
    public ProductOrderRepository productOrderRepository;

    @Autowired
    CustomerRepository customerRepository;

    public void save(ProductOrderDTO productOrderDTO, Integer customerID) {
        ProductOrder productOrder = new ProductOrder();

        productOrder.setStatus(productOrderDTO.getStatus());
        productOrder.setDescription(productOrderDTO.getDescription());
        productOrder.setSubTotal(productOrderDTO.getSubTotal());
        productOrder.setTotal(productOrderDTO.getTotal());
        productOrder.setMomentRequested(LocalDateTime.now());

       Customer customer = customerRepository.findById(customerID).orElse(null);
       if(customer != null) {
           productOrder.setCustomer(customer);
           customer.getAllOrders().add(productOrder);
           customerRepository.save(customer);
       }

        productOrderRepository.save(productOrder);
    }


//    public void delete(Integer orderID) {
//        productOrderRepository.delete(productOrderRepository.findById(orderID).orElse(null));
//    }
}
