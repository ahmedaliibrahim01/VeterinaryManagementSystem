package com.ahmed.veterinaryManagementSystem.model;

import jakarta.persistence.Entity;

@Entity
public class Doctor {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;

}
