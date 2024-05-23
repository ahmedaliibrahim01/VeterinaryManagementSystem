package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.converter.DoctorConverter;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.DoctorResponse;
import com.ahmed.veterinaryManagementSystem.entity.Doctor;
import com.ahmed.veterinaryManagementSystem.repository.DoctorRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The DoctorManager class implements the DoctorService interface and provides methods
 * to manage doctor-related operations.
 */
@Service
public class DoctorManager implements DoctorService {
    private final DoctorConverter doctorConverter;
    private final DoctorRepository doctorRepository;

    // Initializes a new instance of the DoctorManager class with the specified converter and repository.
    public DoctorManager(DoctorConverter converter, DoctorRepository doctorRepository) {
        this.doctorConverter = converter;
        this.doctorRepository = doctorRepository;
    }

    // Saves a new doctor.
    @Override
    public ResultData<DoctorResponse> saveDoctor(DoctorSaveRequest doctorSaveRequest) {
        // Convert the request DTO to a Doctor entity
        Doctor saveDoctor = this.doctorConverter.convertToDoctor(doctorSaveRequest);

        // Check if the doctor already exists
        checkDoctorExistence(saveDoctor);

        // Save the doctor entity
        this.doctorRepository.save(saveDoctor);

        // Return the created doctor response
        return ResultInfo.created(this.doctorConverter.toDoctorResponse(saveDoctor));
    }

    // Updates an existing doctor.
    @Override
    public ResultData<DoctorResponse> updateDoctor(DoctorUpdateRequest doctorUpdateRequest) {
        // Find the existing doctor by ID
        findDoctorById(doctorUpdateRequest.getId());

        // Convert the request DTO to an updated doctor entity
        Doctor updatedDoctor = this.doctorConverter.convertToupdateDoctor(doctorUpdateRequest);

        // Set the ID of the updated doctor
        updatedDoctor.setId(doctorUpdateRequest.getId());

        // Save the updated doctor entity
        this.doctorRepository.save(updatedDoctor);

        // Return the updated doctor response
        return ResultInfo.success(this.doctorConverter.toDoctorResponse(updatedDoctor));
    }

    // Finds a doctor by their ID.
    @Override
    public ResultData<DoctorResponse> findDoctorById(Long id) {
        // Find the doctor by ID in the repository
        Doctor doctor = this.doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + id + " not found."));

        // Return the found doctor response
        return ResultInfo.success(this.doctorConverter.toDoctorResponse(doctor));
    }

    // Deletes a doctor by their ID.
    @Override
    public Result deleteDoctor(Long id) {
        // Find the doctor by ID in the repository
        Doctor doctor = this.doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + id + " not found."));

        // Delete the doctor from the repository
        this.doctorRepository.delete(doctor);

        // Return a success result
        return ResultInfo.ok();
    }

    // Finds all doctors.
    @Override
    public ResultData<List<DoctorResponse>> findAllDoctors() {
        // Retrieve all doctors from the repository
        List<Doctor> allDoctors = this.doctorRepository.findAll();

        // Convert the list of doctors to a list of doctor responses
        List<DoctorResponse> doctorResponses = allDoctors.stream()
                .map(this.doctorConverter::toDoctorResponse)
                .collect(Collectors.toList());

        // Return the list of doctor responses
        return ResultInfo.success(doctorResponses);
    }

    // Finds a doctor by their ID.
    public Doctor findDoctorId(Long id) {
        // Find the doctor by ID in the repository
        return this.doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + id + " not found."));
    }

    // Checks if a doctor already exists based on their email and phone.
    private void checkDoctorExistence(Doctor doctor) {
        // Check if a doctor with the same email or phone already exists
        if (doctorRepository.findByMail(doctor.getMail()).isPresent()) {
            throw new IllegalArgumentException("Email address " + doctor.getMail() + " is already registered.");
        }
        if (doctorRepository.findByPhone(doctor.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Phone " + doctor.getPhone() + " is already registered.");
        }
    }
}
