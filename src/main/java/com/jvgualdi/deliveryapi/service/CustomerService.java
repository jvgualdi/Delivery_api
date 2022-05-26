package com.jvgualdi.deliveryapi.service;

import com.jvgualdi.deliveryapi.dto.CustomerDTO;
import com.jvgualdi.deliveryapi.model.Customer;
import com.jvgualdi.deliveryapi.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    public void save (CustomerDTO customerDTO){
        Customer customer = new Customer();

        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setPassword(customerDTO.getPassword());
        customer.setAddress(customerDTO.getAddress());

        customerRepository.save(customer);
    }

    public void delete(Integer customerID) {
        customerRepository.delete(customerRepository.findById(customerID).orElse(null));
    }
}
