package com.jvgualdi.deliveryapi.dto;

import com.jvgualdi.deliveryapi.model.DeliveryStatus;

public class DeliveryDTO {

    private double tax;
    private DeliveryStatus deliveryStatus;

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
