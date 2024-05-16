package com.ahmed.veterinaryManagementSystem.dto.request.customer;

import jakarta.persistence.Id;
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
    private String city;
}
