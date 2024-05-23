package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * The AppointmentUpdateRequest class is a data transfer object used for updating existing appointments.
 */
@Data
public class AppointmentUpdateRequest {
    @NotNull(message = "Appointment id cannot be null.")
    @Positive(message = "Appointment id must be positive.")
    private Long id;
    @NotNull(message = "Appointment Date cannot be empty")
    private LocalDateTime appointmentDateTime;
    @NotNull(message = "Animal id cannot be empty.")
    @NotNull(message = "Animal id cannot be null.")
    @Positive(message = "Animal id must be positive.")
    private Long animalId;
    @NotNull(message = "Animal id cannot be empty.")
    @NotNull(message = "Animal id cannot be null.")
    @Positive(message = "Animal id must be positive.")
    private Long doctorId;
}
