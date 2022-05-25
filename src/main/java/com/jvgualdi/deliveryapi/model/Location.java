package com.jvgualdi.deliveryapi.model;

import javax.persistence.*;

@Embeddable
public class Location {

    @Column(length = 65)
    private String city;

    @Column
    private String state;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int number;

    @Column
    private int apartmentNumber;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
