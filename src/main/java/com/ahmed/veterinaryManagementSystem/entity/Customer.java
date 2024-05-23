package com.ahmed.veterinaryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * The Customer class represents customer information in the veterinary management system.
 */
@Entity
@Data
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String mail;
    private String address;
    private String city;

    /**
     * The list of animals associated with the customer.
     * This list contains animals that are owned by the customer.
     * The "mappedBy = "customer"" expression specifies the relationship managed by the "customer" field in the Animal class.
     * The "cascade = CascadeType.REMOVE" expression ensures that when a customer is deleted,
     * the associated animals are automatically removed as well.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<Animal> animals;
}
