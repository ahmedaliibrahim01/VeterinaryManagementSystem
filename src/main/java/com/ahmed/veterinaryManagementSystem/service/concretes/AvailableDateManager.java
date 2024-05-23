package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.converter.AvailableDateConverter;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AvailableDateResponse;
import com.ahmed.veterinaryManagementSystem.entity.AvailableDate;
import com.ahmed.veterinaryManagementSystem.repository.AvailableDateRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AvailableDateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The AvailableDateManager class provides concrete implementations for managing available dates
 * for veterinary appointments.
 */
@Service
public class AvailableDateManager implements AvailableDateService {
    private final AvailableDateConverter converterAvailableDate;
    private final AvailableDateRepository availableDateRepository;
    private final DoctorManager doctorManager;
    // Constructs an instance of AvailableDateManager.
    public AvailableDateManager(AvailableDateConverter converter,
                                AvailableDateRepository availableDateRepository, DoctorManager doctorManager) {
        this.converterAvailableDate = converter;
        this.availableDateRepository = availableDateRepository;
        this.doctorManager = doctorManager;
    }
    // Saves a new available date if it does not already exist.
    @Override
    public ResultData<AvailableDateResponse> saveAvailableDate(AvailableDateSaveRequest availableDateSaveRequest) {
        checkIfAvailableDateExists(availableDateSaveRequest);
        this.doctorManager.findDoctorById(availableDateSaveRequest.getDoctorId());
        // Save the available date
        AvailableDate saveAvailableDate = this.converterAvailableDate.convertToAvailableDate(availableDateSaveRequest);
        this.availableDateRepository.save(saveAvailableDate);
        return ResultInfo.created(this.converterAvailableDate.toAvailableDateResponse(saveAvailableDate));
    }
    // Checks if an available date already exists for the selected doctor and specified date.
    private void checkIfAvailableDateExists(AvailableDateSaveRequest availableDateSaveRequest) {
        // Check if an available date with the same doctor ID and date already exists
        Optional<AvailableDate> existingAvailableDate = availableDateRepository.findByDoctorIdAndAvailableDate(
                availableDateSaveRequest.getDoctorId(), availableDateSaveRequest.getAvailableDate());
        if (existingAvailableDate.isPresent()) {
            throw new IllegalArgumentException("An available date for the selected doctor " +
                    "already exists for the specified date.");
        }
    }
    // Updates an existing available date.
    @Override
    public ResultData<AvailableDateResponse> updateAvailableDate(AvailableDateUpdateRequest availableDateUpdateRequest) {
        // Check if the available date exists by its ID
        findAvailableDateById(availableDateUpdateRequest.getId());

        // Convert the request DTO to an entity object
        AvailableDate updatedAvailableDate = this.converterAvailableDate.convertToUpdateAvailableDate(
                availableDateUpdateRequest);
        // Set the ID of the updated available date
        updatedAvailableDate.setId(availableDateUpdateRequest.getId());
        // Save the updated available date to the database
        this.availableDateRepository.save(updatedAvailableDate);

        // Return a success response with the updated available date DTO
        return ResultInfo.success(this.converterAvailableDate.toAvailableDateResponse(updatedAvailableDate));
    }
    // Finds an available date by its ID.
    @Override
    public ResultData<AvailableDateResponse> findAvailableDateById(Long id) {
        // Find the available date by its ID
        AvailableDate availableDate = this.availableDateRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("AvailableDate with ID " + id + " not found."));
        // Convert the found entity to a response DTO
        return ResultInfo.success(this.converterAvailableDate.toAvailableDateResponse(availableDate));
    }

    @Override
    public ResultData<List<AvailableDateResponse>> findAllAvailableDates() {
        // Find all animals
        List<AvailableDate> allAvailableDate = this.availableDateRepository.findAll();
        // Map animal entities to animal responses
        List<AvailableDateResponse> availableDateResponses = allAvailableDate.stream()
                .map(this.converterAvailableDate::toAvailableDateResponse)
                .collect(Collectors.toList());
        // Return success result with all animals data
        return ResultInfo.success(availableDateResponses);
    }
    // Deletes an available date by its ID.
    @Override
    public Result deleteAvailableDate(Long id) {
        // Find the available date by its ID
        AvailableDate availableDate = this.availableDateRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("AvailableDate with ID " + id + " not found."));
        // Delete the available date from the database
        this.availableDateRepository.delete(availableDate);
        // Return a success result
        return ResultInfo.ok();
    }
    // Checks if a doctor is available on the specified date.
    public boolean availableDoctor(Long doctorId, LocalDate availableDate) {
        boolean doctorAvailable = this.availableDateRepository
                .findByDoctorIdAndAvailableDate(doctorId, availableDate).isPresent();
        if (!doctorAvailable) {
            throw new IllegalArgumentException("Doctor is not available on the selected date.");
        }
        return true;
    }
}
