package com.ahmed.veterinaryManagementSystem.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
/**
 * The AppointmentResponse class is a data transfer object used for providing details of an appointment.
 */
@Data
public class AppointmentResponse {
    private Long id;
    private LocalDateTime appointmentDateTime;
    private Long animalId;
    private Long doctorId;
}
