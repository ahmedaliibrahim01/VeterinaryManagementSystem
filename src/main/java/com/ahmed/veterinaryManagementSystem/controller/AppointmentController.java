package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AppointmentResponse;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Endpoint to save a new appointment
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> saveAppointment(
            @Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
        return this.appointmentService.saveAppointment(appointmentSaveRequest);
    }

    // Endpoint to find an appointment by its ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> findAppointmentById(@PathVariable("id") Long id) {
        return this.appointmentService.findAppointmentById(id);
    }

    // Endpoint to delete an appointment by its ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteAppointment(@PathVariable("id") Long id) {
        return this.appointmentService.deleteAppointment(id);
    }

    // Endpoint to update an existing appointment
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        return this.appointmentService.updateAppointment(appointmentUpdateRequest);
    }

    // Endpoint to fetch all appointments
    @GetMapping()
    public ResultData<List<AppointmentResponse>> findAllAppointments() {
        return this.appointmentService.findAllAppointments();
    }

    // Endpoint to fetch appointments by date range and doctor ID
    @GetMapping("/appointmentsByDateAndDoctor")
    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorAndDateRange(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end
    ) {
        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

        return appointmentService.getAppointmentsByDoctorAndDateRange(doctorId, startDateTime, endDateTime);
    }

    // Endpoint to fetch appointments by date range and animal ID
    @GetMapping("/appointmentsByDateAndAnimal")
    public ResultData<List<AppointmentResponse>> getAppointmentsByDateAndAnimal(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam Long animalId
    ) {
        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

        return appointmentService.getAppointmentsByDateAndAnimal(startDateTime, endDateTime, animalId);
    }
}
