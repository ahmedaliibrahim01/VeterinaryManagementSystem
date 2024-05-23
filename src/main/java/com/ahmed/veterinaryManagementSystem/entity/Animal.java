package com.ahmed.veterinaryManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
/**
 * The Animal class represents information about an animal in the veterinary management system.
 * Each instance of this class contains details such as the animal's name, species, breed, gender, color, and date of birth.
 * Additionally, it holds references to its owner (a Customer object), vaccines administered to the animal,
 * and appointments scheduled for the animal.
 */
@Entity
@Data
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;

    /**
     * The customer who owns this animal.
     * This field represents the many-to-one relationship with the Customer entity.
     * The "fetch = FetchType.LAZY" attribute specifies lazy loading of the associated customer.
     * The "JoinColumn" annotation specifies the foreign key column in the database table.
     * The "NotNull" annotation ensures that the customer field is always set.
     * The "JsonIgnore" annotation prevents infinite recursion during JSON serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Customer customer;

    /**
     * The set of vaccines associated with the animal.
     * This set contains the vaccines that have been administered to the animal.
     * The "mappedBy = "animal"" attribute specifies the relationship managed by the "animal" field in the Vaccine class.
     * The "cascade = CascadeType.ALL" attribute ensures that when an animal is deleted,
     * the associated vaccines are automatically removed as well.
     */
    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccines;

    /**
     * The set of appointments associated with the animal.
     * This set contains the appointments scheduled for the animal.
     * The "mappedBy = "animal"" attribute specifies the relationship managed by the "animal" field in the Appointment class.
     * The "cascade = CascadeType.ALL" attribute ensures that when an animal is deleted,
     * the associated appointments are automatically removed as well.
     */
    @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;
}
