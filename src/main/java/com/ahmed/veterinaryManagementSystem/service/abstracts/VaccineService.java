package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.VaccineResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * The VaccineService interface defines the contract for managing vaccine-related operations in the veterinary management system.
 * It includes methods for saving, updating, finding by various criteria, and deleting vaccines.
 */
public interface VaccineService {

    // Saves a new vaccine
    ResultData<VaccineResponse> saveVaccine(VaccineSaveRequest vaccineSaveRequest);

    // Updates an existing vaccine
    ResultData<VaccineResponse> updateVaccine(VaccineUpdateRequest vaccineUpdateRequest);

    // Finds a vaccine by its ID
    ResultData<VaccineResponse> findVaccineById(Long id);

    // Retrieves all vaccines
    ResultData<List<VaccineResponse>> findAllVaccines();

    // Deletes a vaccine by its ID
    Result deleteVaccine(Long id);

    // Finds vaccines by animal ID
    ResultData<List<VaccineResponse>> findByAnimalId(Long animalId);

    // Finds expiring vaccines within a date range
    ResultData<List<VaccineResponse>> findExpiringVaccines(LocalDate startDate, LocalDate endDate);
}