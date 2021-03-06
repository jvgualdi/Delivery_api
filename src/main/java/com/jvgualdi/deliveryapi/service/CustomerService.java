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

    public void save(CustomerDTO customerDTO) {
        Customer customer = new Customer();

        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customerRepository.save(customer);
    }

    public void update(Customer customer, CustomerDTO customerDTO) {
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setName(customerDTO.getName());
        customer.setAddress(customerDTO.getAddress());

        customerRepository.save(customer);
    }

}
