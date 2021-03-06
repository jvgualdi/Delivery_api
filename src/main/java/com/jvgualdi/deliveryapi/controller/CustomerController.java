package com.jvgualdi.deliveryapi.controller;

import com.jvgualdi.deliveryapi.dto.CustomerDTO;
import com.jvgualdi.deliveryapi.model.Customer;
import com.jvgualdi.deliveryapi.repository.CustomerRepository;
import com.jvgualdi.deliveryapi.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
@SecurityRequirement(name = "deliveryapi")
public class CustomerController {

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    public CustomerService customerService;

    @PostMapping("/register")
    public void register (@RequestBody CustomerDTO customerDTO){
        customerService.save(customerDTO);
    }

    @GetMapping("/list")
    public List<Customer> findAll (){
        return customerRepository.findAll();
    }

    @GetMapping("/{customerID}")
    public Optional<Customer> findCustomer (@PathVariable("customerID") Integer customerID){
        return customerRepository.findById(customerID);
    }

    @DeleteMapping("/delete")
    public void delete (@RequestParam Integer customerID){
        customerRepository.deleteById(customerID);
    }

    @PutMapping("/update/{customerID}")
    public void update (@PathVariable("customerID") Integer customerID, @RequestBody CustomerDTO customerDTO) throws Exception{
        Customer customer = customerRepository.findById(customerID).orElse(null);
        if (customer != null){
            customerService.update(customer, customerDTO);
        }else{
            throw new Exception("Customer not found");
        }
    }

}
