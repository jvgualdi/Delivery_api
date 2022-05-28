package com.jvgualdi.deliveryapi.controller;


import com.jvgualdi.deliveryapi.dto.DeliveryDTO;
import com.jvgualdi.deliveryapi.model.Delivery;
import com.jvgualdi.deliveryapi.repository.DeliveryRepository;
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

    @PostMapping("/register/{order_ID}")
    public void register (@PathVariable("order_ID") Integer order_ID, @RequestBody DeliveryDTO deliveryDTO){
        deliveryService.save(deliveryDTO, order_ID);
    }

    @GetMapping("/find-all")
    public List<Delivery> allDeliveries (){
        return deliveryRepository.findAll();
    }

    @DeleteMapping("/delete/{deliveryID}")
    public void delete (@PathVariable("deliveryID") Integer deliveryID){
        deliveryRepository.deleteById(deliveryID);
    }
//
//    @PutMapping
//    public void update (@RequestBody DeliveryDTO deliveryDTO){
//        deliveryService.save(deliveryDTO);
//    }
}
