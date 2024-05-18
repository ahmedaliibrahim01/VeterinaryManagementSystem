package com.ahmed.veterinaryManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime appointmentDateTime;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    @JsonIgnore
    private Animal animal;

    @ManyToOne()
    @NotNull
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @JsonIgnore
    private Doctor doctor;
}
