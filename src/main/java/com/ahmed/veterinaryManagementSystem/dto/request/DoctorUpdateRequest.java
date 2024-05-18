package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DoctorUpdateRequest {
    @NotNull(message = "Doctor id cannot be null.")
    @Positive(message = "Doctor id must be positive.")
    private Long id;
    @NotNull(message = "Doctor name cannot be empty.")
    private String name;
    private String phone;
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String mail;
    private String address;
    private String city;
}
