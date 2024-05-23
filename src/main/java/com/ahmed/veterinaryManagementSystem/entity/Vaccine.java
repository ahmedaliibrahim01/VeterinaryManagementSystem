package com.ahmed.veterinaryManagementSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * The Vaccine class represents information about a vaccine administered to an animal in the veterinary management system.
 * Each instance of this class contains details such as the vaccine's name, code, start and finish dates of protection,
 * and the animal to which the vaccine is administered.
 */
@Entity
@Data
@Table(name = "vaccines")
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;

    /**
     * The animal to which this vaccine is administered.
     * This field represents the many-to-one relationship with the Animal entity.
     * The "JoinColumn" annotation specifies the foreign key column in the database table.
     * The "NotNull" annotation ensures that the animal field is always set.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;
}
