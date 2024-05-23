package com.ahmed.veterinaryManagementSystem.dto.converter;

import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AppointmentResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Appointment;
import com.ahmed.veterinaryManagementSystem.entity.Doctor;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.repository.DoctorRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The AppointmentConverter class provides methods to convert between Appointment entities
 * and their corresponding DTOs for save, update, and response operations.
 */
@Component
public class AppointmentConverter {
    private final AnimalRepository animalRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentConverter(AnimalRepository animalRepository, DoctorRepository doctorRepository) {
        this.animalRepository = animalRepository;
        this.doctorRepository = doctorRepository;
    }

    /**
     * Converts an AppointmentSaveRequest DTO to an Appointment entity.
     *
     * @param appointmentSaveRequest the request DTO containing the appointment details to save
     * @return the Appointment entity or null if the request DTO is null
     */
    public Appointment saveAppointment(AppointmentSaveRequest appointmentSaveRequest) {
        if (appointmentSaveRequest == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(appointmentSaveRequest.getAppointmentDateTime());
        Animal animal = animalRepository.findById(appointmentSaveRequest.getAnimalId()).get();
        appointment.setAnimal(animal);
        Doctor doctor = doctorRepository.findById(appointmentSaveRequest.getDoctorId()).get();
        appointment.setDoctor(doctor);
        return appointment;
    }

    /**
     * Converts an AppointmentUpdateRequest DTO to an Appointment entity.
     *
     * @param appointmentUpdateRequest the request DTO containing the updated appointment details
     * @return the Appointment entity or null if the request DTO is null
     */
    public Appointment updateAppointment(AppointmentUpdateRequest appointmentUpdateRequest) {
        if (appointmentUpdateRequest == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setId(appointmentUpdateRequest.getId());
        appointment.setAppointmentDateTime(appointmentUpdateRequest.getAppointmentDateTime());
        Animal animal = animalRepository.findById(appointmentUpdateRequest.getAnimalId()).get();
        appointment.setAnimal(animal);
        Doctor doctor = doctorRepository.findById(appointmentUpdateRequest.getDoctorId()).get();
        appointment.setDoctor(doctor);
        return appointment;
    }

    /**
     * Converts an Appointment entity to an AppointmentResponse DTO.
     *
     * @param appointment the Appointment entity to convert
     * @return the AppointmentResponse DTO or null if the entity is null
     */
    public AppointmentResponse toAppointmentResponse(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        AppointmentResponse response = new AppointmentResponse();
        response.setId(appointment.getId());
        response.setAppointmentDateTime(appointment.getAppointmentDateTime());
        response.setAnimalId(appointment.getAnimal().getId());
        response.setDoctorId(appointment.getDoctor().getId());
        return response;
    }

    /**
     * Converts a list of Appointment entities to a list of AppointmentResponse DTOs.
     *
     * @param appointments the list of Appointment entities to convert
     * @return the list of AppointmentResponse DTOs
     */
    public List<AppointmentResponse> toAppointmentResponseList(List<Appointment> appointments) {
        return appointments.stream()
                .map(this::toAppointmentResponse)
                .collect(Collectors.toList());
    }
}
