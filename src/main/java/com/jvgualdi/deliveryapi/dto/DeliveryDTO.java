package com.jvgualdi.deliveryapi.dto;

import com.jvgualdi.deliveryapi.model.Location;
import com.jvgualdi.deliveryapi.model.ProductOrder;

import java.time.LocalDateTime;

public class DeliveryDTO {

    private double tax;
    private LocalDateTime timeDelivered;
    private Location address;

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public LocalDateTime getTimeDelivered() {
        return timeDelivered;
    }

    public void setTimeDelivered(LocalDateTime timeDelivered) {
        this.timeDelivered = timeDelivered;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }
}
