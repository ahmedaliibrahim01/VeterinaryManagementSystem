package com.ahmed.veterinaryManagementSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;
/**
 * The Doctor class represents information about a doctor in the veterinary management system.
 */
@Entity
@Data
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String mail;
    private String address;
    private String city;

    /**
     * The set of available dates for appointments managed by the doctor.
     * This set contains the dates when the doctor is available for appointments.
     * The "mappedBy = "doctor"" expression specifies the relationship managed by the "doctor" field in the AvailableDate class.
     * The "cascade = CascadeType.REMOVE" expression ensures that when a doctor is deleted,
     * the associated available dates are automatically removed as well.
     */
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    private List<AvailableDate> availableDates;

    /**
     * The set of appointments assigned to the doctor.
     * This set contains appointments that are assigned to the doctor.
     * The "mappedBy = "doctor"" expression specifies the relationship managed by the "doctor" field in the Appointment class.
     * The "cascade = CascadeType.REMOVE" expression ensures that when a doctor is deleted,
     * the associated appointments are automatically removed as well.
     */
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    private List<Appointment> appointments;
}
