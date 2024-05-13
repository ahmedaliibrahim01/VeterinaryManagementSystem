package com.ahmed.veterinaryManagementSystem.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class AvailableDate {
    private Long id;
    private LocalDate availableDate;
}
