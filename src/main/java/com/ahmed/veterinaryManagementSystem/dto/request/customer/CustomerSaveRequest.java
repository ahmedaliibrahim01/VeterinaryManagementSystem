package com.ahmed.veterinaryManagementSystem.dto.request.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerSaveRequest {
    @NotNull(message = "Customer name cannot be empty.")
    private String name;
    private String phone;
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String mail;
    private String address;
    private String city;
}
