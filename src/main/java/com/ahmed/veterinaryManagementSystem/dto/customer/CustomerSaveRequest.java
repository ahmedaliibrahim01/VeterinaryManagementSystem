package com.ahmed.veterinaryManagementSystem.dto.customer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerSaveRequest {
    @NotNull(message = "Customer name cannot be empty.")
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
