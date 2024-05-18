package com.ahmed.veterinaryManagementSystem.dto.mapper;

import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AvailableDateResponse;
import com.ahmed.veterinaryManagementSystem.entity.AvailableDate;
import com.ahmed.veterinaryManagementSystem.entity.Doctor;
import com.ahmed.veterinaryManagementSystem.repository.DoctorRepository;
import org.springframework.stereotype.Component;

@Component
public class AvailableDateMapper {
    private final DoctorRepository doctorRepository;

    public AvailableDateMapper(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public AvailableDate saveAvailableDate(AvailableDateSaveRequest availableDateSaveRequest) {
        if (availableDateSaveRequest == null) {
            return null;
        }
        AvailableDate availableDate = new AvailableDate();
        availableDate.setAvailable(availableDateSaveRequest.getAvailableDate());
        Doctor doctor = doctorRepository.findById(availableDateSaveRequest.getDoctorId()).get();
        availableDate.setDoctor(doctor);
        return availableDate;
    }

    public AvailableDate updateAvailableDate(AvailableDateUpdateRequest availableDateUpdateRequest) {
        if (availableDateUpdateRequest == null) {
            return null;
        }
        AvailableDate availableDate = new AvailableDate();
        availableDate.setId(availableDateUpdateRequest.getId());
        availableDate.setAvailable(availableDateUpdateRequest.getAvailableDate());
        Doctor doctor = doctorRepository.findById(availableDateUpdateRequest.getDoctorId()).get();
        availableDate.setDoctor(doctor);
        return availableDate;
    }

    public AvailableDateResponse toAvailableDateResponse(AvailableDate availableDate) {
        if (availableDate == null) {
            return null;
        }
        AvailableDateResponse response = new AvailableDateResponse();
        response.setId(availableDate.getId());
        response.setAvailableDate(availableDate.getAvailable());
        response.setDoctorId(availableDate.getDoctor().getId());
        return response;
    }
}
