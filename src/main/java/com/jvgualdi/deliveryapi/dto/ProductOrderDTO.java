package com.jvgualdi.deliveryapi.dto;


import com.jvgualdi.deliveryapi.model.Customer;
import com.jvgualdi.deliveryapi.model.Delivery;
import com.jvgualdi.deliveryapi.model.OrderStatus;
import com.jvgualdi.deliveryapi.model.Product;

import java.util.List;


public class ProductOrderDTO {

    private String description;
    private double subTotal;
    private double total;
    private OrderStatus status;
    private List<Product> products;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
