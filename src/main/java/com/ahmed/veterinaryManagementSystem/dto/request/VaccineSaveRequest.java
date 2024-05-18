package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

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
