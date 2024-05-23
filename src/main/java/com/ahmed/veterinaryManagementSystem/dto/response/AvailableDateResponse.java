package com.ahmed.veterinaryManagementSystem.dto.response;

import lombok.Data;

import java.time.LocalDate;

/**
 * The AvailableDateResponse class represents the response sent to the client for an AvailableDate object.
 * It contains the information necessary to represent an AvailableDate, including its ID, available date, and the ID of the associated doctor.
 * The @Data annotation from Lombok generates getter and setter methods for all fields automatically.
 */
@Data
public class AvailableDateResponse {
    private Long id;
    private LocalDate availableDate;
    private Long doctorId;
}
