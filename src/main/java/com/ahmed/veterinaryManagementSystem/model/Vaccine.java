package com.ahmed.veterinaryManagementSystem.model;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Vaccine {
    private Long id;
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
}
