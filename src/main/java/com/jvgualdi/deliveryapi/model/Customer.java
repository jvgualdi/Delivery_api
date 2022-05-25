package com.jvgualdi.deliveryapi.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_seq", sequenceName = "ID_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @Column(name = "ID")
    private Integer id;

    @Column(length = 80, name = "customer_name", nullable = false)
    private String name;

    private String phoneNumber;

    @Column(name = "customer_email", nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Embedded
    private Location address;

    @OneToMany(targetEntity = ProductOrder.class, cascade = CascadeType.ALL)
    private List<ProductOrder> allOrders;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Location getAddress() {
        return address;
    }

    public void setAddress(Location address) {
        this.address = address;
    }

    public List<ProductOrder> getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(List<ProductOrder> allOrders) {
        this.allOrders = allOrders;
    }
}
