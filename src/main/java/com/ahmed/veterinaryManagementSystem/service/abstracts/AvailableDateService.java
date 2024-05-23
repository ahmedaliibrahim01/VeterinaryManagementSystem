package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AvailableDateResponse;

import java.util.List;

/**
 * The AvailableDateService interface provides methods for managing available dates
 * for veterinary appointments.
 */
public interface AvailableDateService {
    // Saves a new available date.
    ResultData<AvailableDateResponse> saveAvailableDate(AvailableDateSaveRequest availableDateSaveRequest);
    // Updates an existing available dat
    ResultData<AvailableDateResponse> updateAvailableDate(AvailableDateUpdateRequest availableDateUpdateRequest);
    // Retrieves all Available Dates
    ResultData<List<AvailableDateResponse>> findAllAvailableDates();
    // Finds an available date by its ID.
    ResultData<AvailableDateResponse> findAvailableDateById(Long id);

    // Deletes an available date by its ID.
    Result deleteAvailableDate(Long id);
}