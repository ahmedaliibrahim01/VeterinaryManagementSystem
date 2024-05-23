package com.ahmed.veterinaryManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
/**
 * The AvailableDate class represents the dates when a doctor is available in the veterinary management system.
 */
@Entity
@Data
@Table(name = "available_dates")
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate availableDate;

    /**
     * The Doctor associated with this AvailableDate.
     * Each AvailableDate instance represents a date when a specific doctor is available for appointments.
     * The "ManyToOne" relationship indicates that multiple AvailableDate instances can be associated with the same Doctor.
     * The "NotNull" annotation ensures that this field cannot be null, meaning each AvailableDate must be associated with a Doctor.
     * The "JoinColumn" annotation specifies the database column used to store the relationship.
     * In this case, the "doctor_id" column in the AvailableDate table is used to reference the "id" column in the Doctor table.
     * The "JsonIgnore" annotation indicates that this field should be excluded from JSON serialization to avoid circular references.
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Doctor doctor;
}