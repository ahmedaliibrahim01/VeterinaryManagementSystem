package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
/**
 * The AvailableDateUpdateRequest class represents the request for updating an existing AvailableDate instance.
 * It contains the necessary information to update an AvailableDate object, including the ID of the AvailableDate,
 * the new date, and the ID of the associated doctor.
 * The @Data annotation from Lombok generates getter and setter methods for all fields automatically.
 */
@Data
public class AvailableDateUpdateRequest {
    @NotNull(message = "Available Date id cannot be null.")
    @Positive(message = "Available Date id must be positive.")
    private Long id;
    @NotNull(message = "Available Date Date cannot be empty.")
    private LocalDate availableDate;
    @NotNull(message = "Doctor id cannot be empty.")
    @NotNull(message = "Doctor id cannot be null.")
    @Positive(message = "Doctor id must be positive.")
    private Long doctorId;
}
