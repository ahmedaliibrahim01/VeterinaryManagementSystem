package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
/**
 * The CustomerUpdateRequest class represents the request object for updating customer information.
 * It contains fields such as customer id, name, phone number, email address, address, and city.
 */
@Data
public class CustomerUpdateRequest {
    @NotNull(message = "Customer id cannot be null.")
    @Positive(message = "Customer id must be positive.")
    private Long id;
    @NotNull(message = "Customer name cannot be empty.")
    private String name;
    @NotNull(message = "Customer phone cannot be empty.")
    private String phone;
    @Email(message = "Invalid email format. Please provide a valid email address.")
    @NotNull(message = "Customer email cannot be empty.")
    private String mail;
    private String address;
    private String city;
}
