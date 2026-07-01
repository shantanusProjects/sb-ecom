package com.ecommerce.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min=5,message = "Street name must be at least 5 characters")
    private String street;

    @NotBlank
    @Size(min=5,message = "building name must be at least 5 characters")
    private String buildingNae;

    @NotBlank
    @Size(min=4,message = "city name must be at least 4 characters")
    private String city;

    @NotBlank
    @Size(min=2,message = "state name must be at least 2 characters")
    private String state;

    @NotBlank
    @Size(min=2,message = "country name must be at least 2 characters")
    private String country;

    @NotBlank
    @Size(min=6,message = "pincode must be at least 6 characters")
    private String pincode;

    @ToString.Exclude
    @ManyToMany(mappedBy = "addresses")
    private List<User> users = new ArrayList<>();

    public Address(String street, String buildingNae, String city, String state, String country, String pincode) {
        this.street = street;
        this.buildingNae = buildingNae;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
