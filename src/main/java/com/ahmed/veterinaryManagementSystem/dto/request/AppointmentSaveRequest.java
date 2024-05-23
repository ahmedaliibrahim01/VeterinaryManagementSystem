package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;
/**
 * The AppointmentSaveRequest class is a data transfer object used for creating new appointments.
 */
@Data
public class AppointmentSaveRequest {
    @NotNull(message = "Appointment Date and Time cannot be empty")
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
