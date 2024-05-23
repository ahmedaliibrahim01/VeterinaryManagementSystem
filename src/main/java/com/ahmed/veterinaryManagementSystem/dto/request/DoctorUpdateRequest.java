package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
/**
 * The DoctorUpdateRequest class represents the request payload for updating a doctor in the veterinary management system.
 * It contains the necessary fields to update an existing doctor.
 */
@Data
public class DoctorUpdateRequest {
    @NotNull(message = "Doctor id cannot be null.")
    @Positive(message = "Doctor id must be positive.")
    private Long id;
    @NotNull(message = "Doctor name cannot be empty.")
    private String name;
    @NotNull(message = "Doctor name cannot be empty.")
    private String phone;
    @Email(message = "Invalid email format. Please provide a valid email address.")
    @NotNull(message = "Doctor name cannot be empty.")
    private String mail;
    private String address;
    private String city;
}
