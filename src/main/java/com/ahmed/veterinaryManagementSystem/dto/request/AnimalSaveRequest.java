package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
/**
 * The AnimalSaveRequest class represents the request data for saving a new animal in the veterinary management system.
 * It contains fields such as the animal's name, species, breed, gender, color, date of birth, and the ID of the customer who owns the animal.
 * The customer ID is necessary for associating the animal with its owner in the system.
 */
@Data
public class AnimalSaveRequest {
    @NotNull(message = "Animal name cannot be empty.")
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    @NotNull(message = "Customer id cannot be empty.")
    @NotNull(message = "Customer id cannot be null.")
    @Positive(message = "Customer id must be positive.")
    private Long customerId;
}