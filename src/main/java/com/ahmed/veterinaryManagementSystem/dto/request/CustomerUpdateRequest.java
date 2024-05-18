package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CustomerUpdateRequest {
    @NotNull(message = "Customer id cannot be null.")
    @Positive(message = "Customer id must be positive.")
    private Long id;
    @NotNull(message = "Customer name cannot be empty.")
    private String name;
    private String phone;
    private String address;
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String mail;
    private String city;
}
