package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AppointmentResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    // Method for saving an appointment.
    ResultData<AppointmentResponse> saveAppointment(AppointmentSaveRequest appointmentSaveRequest);

    // Method for updating an appointment.
    ResultData<AppointmentResponse> updateAppointment(AppointmentUpdateRequest appointmentUpdateRequest);

    // Method to find an appointment by its identifier.
    ResultData<AppointmentResponse> findAppointmentById(Long id);

    // Method to retrieve all appointments.
    ResultData<List<AppointmentResponse>> findAllAppointments();

    // Method to retrieve appointments by doctor and date range.
    ResultData<List<AppointmentResponse>> getAppointmentsByDoctorAndDateRange(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    // Method to retrieve appointments by date range and animal.
    ResultData<List<AppointmentResponse>> getAppointmentsByDateAndAnimal(LocalDateTime startDateTime, LocalDateTime endDateTime, Long animalId);

    // Method to delete an appointment by its identifier.
    Result deleteAppointment(Long id);
}
