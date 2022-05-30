package com.jvgualdi.deliveryapi.controller;


import com.jvgualdi.deliveryapi.dto.DeliveryDTO;
import com.jvgualdi.deliveryapi.model.Delivery;
import com.jvgualdi.deliveryapi.model.ProductOrder;
import com.jvgualdi.deliveryapi.repository.DeliveryRepository;
import com.jvgualdi.deliveryapi.repository.ProductOrderRepository;
import com.jvgualdi.deliveryapi.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    @Autowired
    public DeliveryRepository deliveryRepository;

    @Autowired
    public DeliveryService deliveryService;

    @Autowired
    public ProductOrderRepository productOrderRepository;

    @PostMapping("/register")
    public void register (@RequestBody DeliveryDTO deliveryDTO) throws Exception {
        ProductOrder productOrder = productOrderRepository.findById(deliveryDTO.getProductOrder().getId()).get();
        if (productOrder.getDelivery() == null)
            deliveryService.save(deliveryDTO);
        else
            throw new Exception("Order could not be registered");
    }

//    @PostMapping("/register/{order_ID}")
//    public void register (@PathVariable("order_ID") Integer order_ID, @RequestBody DeliveryDTO deliveryDTO) throws Exception {
//        ProductOrder productOrder = productOrderRepository.findById(order_ID).orElse(null);
//        if (productOrder != null && productOrder.getDelivery() == null)
//            deliveryService.save(deliveryDTO, order_ID);
//        else
//            throw new Exception("Order not found");
//    }

    @GetMapping("/list")
    public List<Delivery> findAll (){
        return deliveryRepository.findAll();
    }

    @DeleteMapping("/delete/{deliveryID}")
    public void delete (@PathVariable("deliveryID") Integer deliveryID){
        deliveryRepository.deleteById(deliveryID);
    }

    @PutMapping("/update/{deliveryID}")
    public void update (@PathVariable("deliveryID") Integer deliveryID, @RequestBody DeliveryDTO deliveryDTO) throws Exception {
        Delivery delivery = deliveryRepository.findById(deliveryID).orElse(null);
        if (delivery != null){
            deliveryService.update(delivery, deliveryDTO );
        }else{
            throw new Exception("Delivery not found");
        }
    }
}
