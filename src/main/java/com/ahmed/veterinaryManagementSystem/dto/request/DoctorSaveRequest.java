package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * The DoctorSaveRequest class represents the request payload for saving a doctor in the veterinary management system.
 * It contains the necessary fields to create a new doctor.
 */
@Data
public class DoctorSaveRequest {
    @NotNull(message = "Doctor name cannot be empty.")
    private String name;
    @NotNull(message = "Doctor phone cannot be empty.")
    private String phone;
    @Email(message = "Invalid email format. Please provide a valid email address.")
    @NotNull(message = "Doctor email cannot be empty.")
    private String mail;
    private String address;
    private String city;
}
