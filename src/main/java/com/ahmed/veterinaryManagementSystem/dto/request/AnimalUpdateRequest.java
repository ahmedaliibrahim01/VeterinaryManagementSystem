package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
/**
 * The AnimalUpdateRequest class represents the request data for updating an existing animal in the veterinary management system.
 * It contains fields such as the animal's ID, name, species, breed, gender, color, date of birth, and the ID of the customer who owns the animal.
 * The animal ID is necessary to identify the specific animal to be updated, while the customer ID is needed to associate the animal with its owner in the system.
 */
@Data
public class AnimalUpdateRequest {
    @NotNull(message = "Animal name cannot be empty.")
    @NotNull(message = "Animal id cannot be null.")
    @Positive(message = "Animal id must be positive.")
    private Long id;
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
