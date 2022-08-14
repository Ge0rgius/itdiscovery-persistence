package it.discovery.persistence.model;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String city;

    private String street;

    private int apartment;

    private String zip;
}
