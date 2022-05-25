package com.jvgualdi.deliveryapi.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "delivery_tax")
    private BigDecimal tax;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime timeDelivered;

    @OneToOne
    @JoinColumn(name = "order_number", referencedColumnName = "id")
    private Order order;

    @Embedded
    private Location address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public LocalDateTime getTimeDelivered() {
        return timeDelivered;
    }

    public void setTimeDelivered(LocalDateTime timeDelivered) {
        this.timeDelivered = timeDelivered;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }
}
