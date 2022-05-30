package com.jvgualdi.deliveryapi.controller;

import com.jvgualdi.deliveryapi.dto.ProductOrderDTO;
import com.jvgualdi.deliveryapi.model.Customer;
import com.jvgualdi.deliveryapi.model.ProductOrder;
import com.jvgualdi.deliveryapi.repository.CustomerRepository;
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

    @Autowired
    public CustomerRepository customerRepository;

//    @PostMapping("/register/{customer_id}")
//    public void register (@PathVariable("customer_id") Integer customerID, @RequestBody ProductOrderDTO productOrderDTO) throws Exception {
//        Customer customer = customerRepository.findById(customerID).orElse(null);
//        if (productOrderDTO.getSubTotal() <= productOrderDTO.getTotal() && customer != null){
//            productOrderService.save(productOrderDTO, customerID);
//        }else {
//            throw new Exception("Order could not be registered");
//        }
//    }

    @PostMapping("/register")
    public void register (@RequestBody ProductOrderDTO productOrderDTO) throws Exception {
        if (productOrderDTO.getSubTotal() <= productOrderDTO.getTotal() && productOrderDTO.getCustomer() != null){
            productOrderService.save(productOrderDTO);
        }else {
            throw new Exception("Order could not be registered");
        }
    }

    @GetMapping("/list")
    public List<ProductOrder> findAll(){
        return productOrderRepository.findAll();
    }

    @GetMapping("/{customer_id}")
    public List<ProductOrder> findByCustomerID(@PathVariable("customer_id") Integer customerID){
        return productOrderRepository.findByCustomer(customerID);
    }

    @DeleteMapping("/delete/{orderID}")
    public void deleteOrder (@PathVariable("orderID") Integer productOrderID){
        productOrderRepository.deleteById(productOrderID);
    }

    @PutMapping("/update/{orderID}")
    public void updateOrder (@PathVariable("orderID") Integer orderID, @RequestBody ProductOrderDTO productOrderDTO) throws Exception {
        ProductOrder productOrder = productOrderRepository.findById(orderID).orElse(null);
        if (productOrder != null){
            productOrderService.update(productOrder, productOrderDTO);
        }else{
            throw new Exception("Product order not Found");
        }
    }


}
