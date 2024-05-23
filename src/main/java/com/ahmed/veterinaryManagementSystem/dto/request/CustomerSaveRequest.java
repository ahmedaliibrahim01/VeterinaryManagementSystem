package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * The CustomerSaveRequest class represents the request object for saving customer information.
 * It contains fields such as customer name, phone number, email address, address, and city.
 */

@Data
public class CustomerSaveRequest {
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
