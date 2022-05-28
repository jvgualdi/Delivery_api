package com.jvgualdi.deliveryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Embeddable
public class Location {

    @Column(length = 65, nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String neighborhood;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private int number;

}
