package com.ahmed.veterinaryManagementSystem.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDateTime;
    private Long animalId;
    private Long doctorId;
}
