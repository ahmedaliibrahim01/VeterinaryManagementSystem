package com.ahmed.veterinaryManagementSystem.dto.mapper;

import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AppointmentResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Appointment;
import com.ahmed.veterinaryManagementSystem.entity.Customer;
import com.ahmed.veterinaryManagementSystem.entity.Doctor;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.repository.DoctorRepository;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    private final AnimalRepository animalRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentMapper(AnimalRepository animalRepository, DoctorRepository doctorRepository) {
        this.animalRepository = animalRepository;
        this.doctorRepository = doctorRepository;
    }

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

    public Appointment updateAppointment(AppointmentUpdateRequest appointmentUpdateRequest) {
        if (appointmentUpdateRequest == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setId(appointmentUpdateRequest.getId());
        appointment.setAppointmentDateTime(appointmentUpdateRequest.getAppointmentDateTime());
        return appointment;
    }

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
}
