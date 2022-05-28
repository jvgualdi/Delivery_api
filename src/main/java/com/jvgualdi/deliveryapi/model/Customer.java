package com.jvgualdi.deliveryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "customer_seq")
    @Column(name = "ID")
    private Integer id;

    @Column(length = 80, name = "customer_name", nullable = false)
    private String name;

    @Column
    private String phoneNumber;

    @Embedded
    private Location address;

    @OneToMany(targetEntity = ProductOrder.class, cascade = CascadeType.ALL, orphanRemoval= true, mappedBy = "customer")
    //@JoinColumn(name = "customer_id", referencedColumnName = "id")
    private List<ProductOrder> productOrders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }
}
