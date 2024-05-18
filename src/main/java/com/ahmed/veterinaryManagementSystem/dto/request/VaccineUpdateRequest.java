package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

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
