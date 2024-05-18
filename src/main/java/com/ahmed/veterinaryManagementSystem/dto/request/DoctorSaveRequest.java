package com.ahmed.veterinaryManagementSystem.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DoctorSaveRequest {
    @NotNull(message = "Doctor name cannot be empty.")
    private String name;
    private String phone;
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String mail;
    private String address;
    private String city;
}
