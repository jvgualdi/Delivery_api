package com.jvgualdi.deliveryapi.controller;

import com.jvgualdi.deliveryapi.dto.CustomerDTO;
import com.jvgualdi.deliveryapi.model.Customer;
import com.jvgualdi.deliveryapi.repository.CustomerRepository;
import com.jvgualdi.deliveryapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public CustomerService customerService;

    @PostMapping
    public void register (@RequestBody CustomerDTO customerDTO){
        customerService.save(customerDTO);
    }

    @GetMapping
    public List<Customer> findAll (){
        return customerRepository.findAll();
    }

    @DeleteMapping(path = {"/id"})
    public void delete (@PathVariable("id") Integer customerID){
        customerRepository.deleteById(customerID);
    }

//    @PutMapping(path = {"/id"})
//    public void update (@PathVariable Integer customerID){
//        customerService.save(customerID);
//    }

}
