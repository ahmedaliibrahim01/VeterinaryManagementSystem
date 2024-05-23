package com.ahmed.veterinaryManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The Appointment class represents a veterinary appointment entity in the system.
 */
@Entity
@Data
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    /**
     * The animal associated with the appointment.
     * This field is not included in the JSON serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "animal_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Animal animal;

    /**
     * The doctor associated with the appointment.
     * This field is not included in the JSON serialization.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "doctor_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Doctor doctor;
}
