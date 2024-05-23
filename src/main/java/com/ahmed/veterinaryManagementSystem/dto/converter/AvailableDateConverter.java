package com.ahmed.veterinaryManagementSystem.dto.converter;

import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AvailableDateResponse;
import com.ahmed.veterinaryManagementSystem.entity.AvailableDate;
import com.ahmed.veterinaryManagementSystem.entity.Doctor;
import com.ahmed.veterinaryManagementSystem.repository.DoctorRepository;
import org.springframework.stereotype.Component;

/**
 * The AvailableDateConverter class converts between AvailableDate entity objects, AvailableDate request objects, and AvailableDate response objects.
 * It handles conversions for saving and updating AvailableDate entities, as well as converting AvailableDate entities to response objects.
 * The conversion process involves mapping fields between different types of objects.
 * This class is marked as a Spring component, allowing it to be automatically detected and registered during component scanning.
 */
@Component
public class AvailableDateConverter {

    private final DoctorRepository doctorRepository;

    /**
     * Constructs a new AvailableDateConverter with the specified DoctorRepository.
     *
     * @param doctorRepository the repository for accessing Doctor entities
     */
    public AvailableDateConverter(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /**
     * Converts an AvailableDateSaveRequest object to an AvailableDate entity.
     *
     * @param availableDateSaveRequest the request object containing data for creating a new AvailableDate
     * @return the created AvailableDate entity
     */
    public AvailableDate convertToAvailableDate(AvailableDateSaveRequest availableDateSaveRequest) {
        if (availableDateSaveRequest == null) {
            return null;
        }
        AvailableDate availableDate = new AvailableDate();
        availableDate.setAvailableDate(availableDateSaveRequest.getAvailableDate());
        Doctor doctor = doctorRepository.findById(availableDateSaveRequest.getDoctorId()).get();
        availableDate.setDoctor(doctor);
        return availableDate;
    }

    /**
     * Converts an AvailableDateUpdateRequest object to an AvailableDate entity.
     *
     * @param availableDateUpdateRequest the request object containing data for updating an existing AvailableDate
     * @return the updated AvailableDate entity
     */
    public AvailableDate convertToUpdateAvailableDate(AvailableDateUpdateRequest availableDateUpdateRequest) {
        if (availableDateUpdateRequest == null) {
            return null;
        }
        AvailableDate availableDate = new AvailableDate();
        availableDate.setId(availableDateUpdateRequest.getId());
        availableDate.setAvailableDate(availableDateUpdateRequest.getAvailableDate());
        Doctor doctor = doctorRepository.findById(availableDateUpdateRequest.getDoctorId()).get();
        availableDate.setDoctor(doctor);
        return availableDate;
    }

    /**
     * Converts an AvailableDate entity to an AvailableDateResponse object.
     *
     * @param availableDate the AvailableDate entity to convert
     * @return the AvailableDateResponse object representing the entity
     */
    public AvailableDateResponse toAvailableDateResponse(AvailableDate availableDate) {
        if (availableDate == null) {
            return null;
        }
        AvailableDateResponse response = new AvailableDateResponse();
        response.setId(availableDate.getId());
        response.setAvailableDate(availableDate.getAvailableDate());
        response.setDoctorId(availableDate.getDoctor().getId());
        return response;
    }
}
