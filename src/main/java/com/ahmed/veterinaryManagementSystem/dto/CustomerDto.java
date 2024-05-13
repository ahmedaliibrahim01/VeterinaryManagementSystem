package com.ahmed.veterinaryManagementSystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    @NotNull
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
