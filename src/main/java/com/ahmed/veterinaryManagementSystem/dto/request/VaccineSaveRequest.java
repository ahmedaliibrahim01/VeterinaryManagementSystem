package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
/**
 * The VaccineSaveRequest class represents the request data for saving a new vaccine in the veterinary management system.
 * It contains fields such as the vaccine's name, code, start and finish dates of protection, and the ID of the animal to which the vaccine is administered.
 * The animal ID is necessary for associating the vaccine with the corresponding animal in the system.
 */
@Data
public class VaccineSaveRequest {
    @NotNull(message = "Vaccine name cannot be empty.")
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    @NotNull(message = "Vaccine id cannot be empty.")
    private Long animalId;
}
