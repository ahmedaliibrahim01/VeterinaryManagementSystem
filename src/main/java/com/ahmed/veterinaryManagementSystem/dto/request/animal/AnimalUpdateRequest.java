package com.ahmed.veterinaryManagementSystem.dto.request.animal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

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
    private Long customerId;
}
