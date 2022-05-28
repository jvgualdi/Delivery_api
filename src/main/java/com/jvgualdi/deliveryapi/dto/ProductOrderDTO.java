package com.jvgualdi.deliveryapi.dto;

import com.jvgualdi.deliveryapi.model.Delivery;
import com.jvgualdi.deliveryapi.model.OrderStatus;

import java.time.LocalDateTime;

public class ProductOrderDTO {

    private String description;
    private double subTotal;
    private double total;
    private OrderStatus status;

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
