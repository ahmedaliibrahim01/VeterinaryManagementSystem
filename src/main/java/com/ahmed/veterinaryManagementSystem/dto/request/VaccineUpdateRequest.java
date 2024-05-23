package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

/**
 * The VaccineUpdateRequest class represents the request data for updating an existing vaccine in the veterinary management system.
 * It contains fields such as the vaccine's ID, name, code, start and finish dates of protection, and the ID of the animal to which the vaccine is administered.
 * The vaccine ID is necessary to identify the vaccine to be updated.
 * The animal ID is necessary to associate the vaccine with the corresponding animal in the system.
 */
@Data
public class VaccineUpdateRequest {
    @NotNull(message = "Vaccine id cannot be null.")
    @Positive(message = "Vaccine id must be positive.")
    private Long id;
    @NotNull(message = "Vaccine name cannot be empty.")
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    @NotNull(message = "Animal id cannot be null.")
    @Positive(message = "Animal id must be positive.")
    private Long animalId;
}
