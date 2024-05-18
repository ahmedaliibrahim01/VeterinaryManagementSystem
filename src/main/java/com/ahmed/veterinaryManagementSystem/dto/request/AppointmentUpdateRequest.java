package com.ahmed.veterinaryManagementSystem.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentUpdateRequest {
    @NotNull(message = "Appointment id cannot be null.")
    @Positive(message = "Appointment id must be positive.")
    private Long id;
    @NotNull(message = "Appointment Date cannot be empty.")
    private LocalDateTime appointmentDateTime;
    @NotNull(message = "Animal id cannot be empty.")
    @NotNull(message = "Animal id cannot be null.")
    @Positive(message = "Animal id must be positive.")
    private Long animalId;
}
