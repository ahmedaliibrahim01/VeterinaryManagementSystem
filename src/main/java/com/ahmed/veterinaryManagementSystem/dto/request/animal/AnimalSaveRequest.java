package com.ahmed.veterinaryManagementSystem.dto.request.animal;

import com.ahmed.veterinaryManagementSystem.model.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AnimalSaveRequest {
    @NotNull(message = "Animal name cannot be empty.")
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    @NotNull(message = "Customer id cannot be empty.")
    private Long customerId;
}
