package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.converter.AppointmentConverter;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AppointmentResponse;
import com.ahmed.veterinaryManagementSystem.entity.Appointment;
import com.ahmed.veterinaryManagementSystem.repository.AppointmentRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentManager implements AppointmentService {
    private final AppointmentConverter converterAppointment;
    private final AppointmentRepository appointmentRepository;
    private final AnimalManager animalManager;
    private final DoctorManager doctorManager;
    private final AvailableDateManager availableDateManager;

    public AppointmentManager(AppointmentConverter converter,
                              AppointmentRepository appointmentRepository,
                              AnimalManager animalManager,
                              DoctorManager doctorManager,
                              AvailableDateManager availableDateManager) {
        this.converterAppointment = converter;
        this.appointmentRepository = appointmentRepository;
        this.animalManager = animalManager;
        this.doctorManager = doctorManager;
        this.availableDateManager = availableDateManager;
    }

    @Override
    public ResultData<AppointmentResponse> saveAppointment(AppointmentSaveRequest appointmentSaveRequest) {
        this.animalManager.findAnimal(appointmentSaveRequest.getAnimalId());
        this.doctorManager.findDoctorById(appointmentSaveRequest.getDoctorId());
        LocalDate availableDate = appointmentSaveRequest.getAppointmentDateTime().toLocalDate();
        this.availableDateManager.availableDoctor(appointmentSaveRequest.getDoctorId(), availableDate);

        // Check if there is already an appointment at the given date and time
        this.appointmentExists(appointmentSaveRequest.getDoctorId(), appointmentSaveRequest.getAppointmentDateTime());

        Appointment saveAppointment = this.converterAppointment.saveAppointment(appointmentSaveRequest);
        this.appointmentRepository.save(saveAppointment);
        return ResultInfo.created(this.converterAppointment.toAppointmentResponse(saveAppointment));
    }

    @Override
    public ResultData<AppointmentResponse> updateAppointment(AppointmentUpdateRequest appointmentUpdateRequest) {
        this.findAppointment(appointmentUpdateRequest.getId());
        this.animalManager.findAnimal(appointmentUpdateRequest.getAnimalId());
        this.doctorManager.findDoctorById(appointmentUpdateRequest.getDoctorId());
        LocalDate availableDate = appointmentUpdateRequest.getAppointmentDateTime().toLocalDate();
        this.availableDateManager.availableDoctor(appointmentUpdateRequest.getDoctorId(), availableDate);
        Appointment saveAppointment = this.converterAppointment.updateAppointment(appointmentUpdateRequest);
        this.appointmentRepository.save(saveAppointment);
        return ResultInfo.success(this.converterAppointment.toAppointmentResponse(saveAppointment));
    }

    void appointmentExists(Long doctorId, LocalDateTime appointmentDateTime) {
        LocalDateTime startDateTime = appointmentDateTime.withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endDateTime = startDateTime.plusHours(1); // Appointment duration assumed to be 1 hour

        // Is there another appointment within the specified time range to check?
        boolean doctorAvailable = this.appointmentRepository
                .existsByDoctorIdAndAppointmentDateTimeBetween(doctorId, startDateTime, endDateTime);

        if (doctorAvailable) {
            throw new IllegalArgumentException("The doctor already has an appointment within the selected time range.");
        }
    }

    // Finds an appointment by its ID.
    public Appointment findAppointment(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + id));
    }

    @Override
    public ResultData<AppointmentResponse> findAppointmentById(Long id) {
        Appointment appointment = this.findAppointment(id);
        return ResultInfo.success(this.converterAppointment.toAppointmentResponse(appointment));
    }

    @Override
    public ResultData<List<AppointmentResponse>> findAllAppointments() {
        // Find all appointments
        List<Appointment> allAppointments = this.appointmentRepository.findAll();
        // Map appointment entities to appointment responses
        List<AppointmentResponse> appointmentResponses = allAppointments.stream()
                .map(this.converterAppointment::toAppointmentResponse)
                .collect(Collectors.toList());
        // Return success result with all appointments data
        return ResultInfo.success(appointmentResponses);
    }


    @Override
    public Result deleteAppointment(Long id) {
        Appointment appointment = this.findAppointment(id);
        this.appointmentRepository.delete(appointment);
        return ResultInfo.ok();
    }

    @Override
    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorAndDateRange(
            Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Long doctor = this.doctorManager.findDoctorId(doctorId).getId();
        // Fetch appointments of the specified doctor within the specified time range
        List<Appointment> appointments = appointmentRepository
                .findByDoctorIdAndAppointmentDateTimeBetween(doctor, startDateTime, endDateTime);

        // Convert appointment objects to AppointmentResponse objects
        List<AppointmentResponse> appointmentResponses = converterAppointment.toAppointmentResponseList(appointments);

        // Return the result data
        return ResultInfo.success(appointmentResponses);
    }

    @Override
    public ResultData<List<AppointmentResponse>> getAppointmentsByDateAndAnimal(
            LocalDateTime startDateTime, LocalDateTime endDateTime, Long animalId) {
        this.animalManager.findAnimalById(animalId);
        // Fetch appointments of the specified animal within the specified time range
        List<Appointment> appointments = appointmentRepository.findByAnimalIdAndAppointmentDateTimeBetween(animalId, startDateTime, endDateTime);

        // Convert appointment objects to AppointmentResponse objects
        List<AppointmentResponse> appointmentResponses = converterAppointment.toAppointmentResponseList(appointments);

        // Return the result data
        return ResultInfo.success(appointmentResponses);
    }
}