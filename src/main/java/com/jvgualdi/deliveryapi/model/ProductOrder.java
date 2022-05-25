package com.jvgualdi.deliveryapi.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class ProductOrder {

    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @Column(name = "ID")
    private Integer id;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime momentRequested;

    @Column(length = 300)
    private String description;

    @Column(name = "sub_total", nullable = false)
    private double subTotal;

    @Column(nullable = false)
    private double total;

    @OneToOne(mappedBy = "productOrder")
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getMomentRequested() {
        return momentRequested;
    }

    public void setMomentRequested(LocalDateTime momentRequested) {
        this.momentRequested = momentRequested;
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

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

}
