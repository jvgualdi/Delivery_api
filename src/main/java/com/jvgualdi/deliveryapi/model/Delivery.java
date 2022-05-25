package com.jvgualdi.deliveryapi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Delivery {

    @Id
    @SequenceGenerator(name = "delivery_seq", sequenceName = "ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_seq")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "delivery_tax")
    private double tax;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime timeDelivered;

    @OneToOne
    @JoinColumn(name = "request_number", referencedColumnName = "id")
    private ProductOrder productOrder;

    @Embedded
    private Location address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public ProductOrder getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
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
