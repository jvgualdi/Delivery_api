package com.jvgualdi.deliveryapi.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime momentRequested;

    @Column(length = 300)
    private String description;

    @Column(name = "sub_total", nullable = false)
    private BigDecimal subTotal;

    @Column(nullable = false)
    private BigDecimal total;

    @OneToOne(mappedBy = "order")
    private Delivery delivery;

//    @ManyToOne
//    @JoinColumn(name = "customerOrder_fkey", referencedColumnName = "id")
//    private Customer customer;


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

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
