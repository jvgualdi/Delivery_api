package com.jvgualdi.deliveryapi.controller;

import com.jvgualdi.deliveryapi.dto.ProductOrderDTO;
import com.jvgualdi.deliveryapi.model.ProductOrder;
import com.jvgualdi.deliveryapi.repository.ProductOrderRepository;
import com.jvgualdi.deliveryapi.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/orders")
public class ProductOrderController {

    @Autowired
    public ProductOrderRepository productOrderRepository;

    @Autowired
    public ProductOrderService productOrderService;

    @PostMapping("/register/{customerID}")
    public void register (@PathVariable("customerID") Integer customerID, @RequestBody ProductOrderDTO productOrderDTO){
        productOrderService.save(productOrderDTO, customerID);
    }

    @GetMapping("/find-all")
    public List<ProductOrder> findAll(){
        return productOrderRepository.findAll();
    }

    @DeleteMapping("/delete/{orderID}")
    public void deleteOrder (@PathVariable("orderID") Integer productOrderID){
        productOrderRepository.deleteById(productOrderID);
    }

    @PutMapping
    public void updateOrder (@RequestBody ProductOrderDTO productOrderDTO){
    }


}
