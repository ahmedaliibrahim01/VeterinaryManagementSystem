package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.DoctorResponse;

import java.util.List;

/**
 * The DoctorService interface declares methods for managing doctor-related operations.
 */
public interface DoctorService {
    // Saves a new doctor.
    ResultData<DoctorResponse> saveDoctor(DoctorSaveRequest doctorSaveRequest);
    // Updates an existing doctor.
    ResultData<DoctorResponse> updateDoctor(DoctorUpdateRequest doctorUpdateRequest);
    // Finds a doctor by ID.
    ResultData<DoctorResponse> findDoctorById(Long id);
    // Deletes a doctor by ID.
    Result deleteDoctor(Long id);
    // Finds all doctors.
    ResultData<List<DoctorResponse>> findAllDoctors();
}
