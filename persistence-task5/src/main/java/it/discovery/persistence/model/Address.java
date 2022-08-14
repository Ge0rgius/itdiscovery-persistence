package it.discovery.persistence.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Address {

    private String city;

    private String street;

    private int apartment;

    private String zip;
}
