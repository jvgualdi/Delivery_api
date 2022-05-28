package com.jvgualdi.deliveryapi.dto;

import com.jvgualdi.deliveryapi.model.Location;
import com.jvgualdi.deliveryapi.model.ProductOrder;

import java.time.LocalDateTime;

public class DeliveryDTO {

    private double tax;

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

}
