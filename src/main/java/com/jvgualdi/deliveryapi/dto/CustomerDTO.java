package com.jvgualdi.deliveryapi.dto;

import com.jvgualdi.deliveryapi.model.Location;

public class CustomerDTO {

    private String name;
    private String phoneNumber;
    private Location address;

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
}
