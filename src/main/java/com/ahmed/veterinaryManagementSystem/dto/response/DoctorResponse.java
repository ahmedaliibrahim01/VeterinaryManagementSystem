package com.ahmed.veterinaryManagementSystem.dto.response;

import lombok.Data;
/**
 * The DoctorResponse class represents the response payload for retrieving doctor information in the veterinary management system.
 * It contains the fields representing the details of a doctor.
 */
@Data
public class DoctorResponse {
    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
