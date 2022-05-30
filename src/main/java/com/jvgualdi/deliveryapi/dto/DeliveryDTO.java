package com.jvgualdi.deliveryapi.dto;

import com.jvgualdi.deliveryapi.model.DeliveryStatus;
import com.jvgualdi.deliveryapi.model.ProductOrder;

public class DeliveryDTO {

    private double tax;
    private DeliveryStatus deliveryStatus;
    private ProductOrder productOrder;

    public ProductOrder getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }

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
