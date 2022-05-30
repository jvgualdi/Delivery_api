package com.jvgualdi.deliveryapi.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    private Integer id;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
