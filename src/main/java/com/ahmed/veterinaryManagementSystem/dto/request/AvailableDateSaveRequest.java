package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
/**
 * The AvailableDateSaveRequest class represents the request for creating a new AvailableDate instance.
 * It contains the necessary information to create an AvailableDate object, including the date and the ID of the associated doctor.
 * The @Data annotation from Lombok generates getter and setter methods for all fields automatically.
 */

@Data
public class AvailableDateSaveRequest {
    @NotNull(message = "Available Date cannot be empty")
    private LocalDate availableDate;
    @NotNull(message = "Doctor id cannot be empty.")
    @NotNull(message = "Doctor id cannot be null.")
    @Positive(message = "Doctor id must be positive.")
    private Long doctorId;
}
