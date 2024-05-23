package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.converter.AnimalConverter;
import com.ahmed.veterinaryManagementSystem.dto.converter.VaccineConverter;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.VaccineResponse;
import com.ahmed.veterinaryManagementSystem.entity.Vaccine;
import com.ahmed.veterinaryManagementSystem.repository.VaccineRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.VaccineService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The VaccineManager class implements the VaccineService interface and provides methods
 * to manage vaccine-related operations.
 */
@Service
public class VaccineManager implements VaccineService {

    private final VaccineRepository vaccineRepository;
    private final AnimalManager animalManager;
    private final VaccineConverter converter;

    // Initializes a new instance of the VaccineManager class with the specified repository, animal manager, and converters.
    public VaccineManager(VaccineRepository vaccineRepository, AnimalManager animalManager, VaccineConverter converter, AnimalConverter animalConverter) {
        this.vaccineRepository = vaccineRepository;
        this.animalManager = animalManager;
        this.converter = converter;
    }

    // Saves a new vaccine.
    @Override
    public ResultData<VaccineResponse> saveVaccine(VaccineSaveRequest vaccineSaveRequest) {
        // Find the associated animal by ID
        this.animalManager.findAnimalById(vaccineSaveRequest.getAnimalId());

        // Check for existing vaccines with the same name, code, and animal ID
        validateExistingVaccines(vaccineSaveRequest);

        // Save the new vaccine to the repository
        Vaccine saveVaccine = this.converter.convertToVaccine(vaccineSaveRequest);
        this.vaccineRepository.save(saveVaccine);

        // Return the created vaccine as response
        return ResultInfo.created(this.converter.toVaccineResponse(saveVaccine));
    }

    // Validates if there are any existing vaccines with the same name, code, and animal ID, and if the dates are valid.
    private void validateExistingVaccines(VaccineSaveRequest vaccineSaveRequest) {
        // Get existing vaccines with the same name, code, and animal ID
        List<Vaccine> existingVaccines = vaccineRepository.findByNameAndCodeAndAnimalId(
                vaccineSaveRequest.getName(),
                vaccineSaveRequest.getCode(),
                vaccineSaveRequest.getAnimalId()
        );

        // Check if the start date is before the finish date
        if (!vaccineSaveRequest.getProtectionStartDate().isBefore(vaccineSaveRequest.getProtectionFinishDate())) {
            throw new IllegalArgumentException("The protection start date must be before the protection finish date.");
        }

        // Check if any existing vaccine has a protection finish date that hasn't passed
        for (Vaccine existingVaccine : existingVaccines) {
            if (!existingVaccine.getProtectionFinishDate().isBefore(vaccineSaveRequest.getProtectionFinishDate())) {
                throw new IllegalArgumentException("A vaccine with the same name, code, and animal ID already exists" +
                        " with a protection finish date in the future or overlapping the new vaccine's dates.");
            }
        }
    }

    // Updates an existing vaccine.
    @Override
    public ResultData<VaccineResponse> updateVaccine(VaccineUpdateRequest vaccineUpdateRequest) {
        // Find the existing vaccine by ID
        this.findVaccineById(vaccineUpdateRequest.getId());

        // Find the associated animal by ID
        this.animalManager.findAnimalById(vaccineUpdateRequest.getAnimalId());

        // Convert the request DTO to an updated vaccine entity
        Vaccine updateVaccine = this.converter.convertToUpdateVaccine(vaccineUpdateRequest);

        // Save the updated vaccine entity
        this.vaccineRepository.save(updateVaccine);

        // Return the updated vaccine response
        return ResultInfo.success(this.converter.toVaccineResponse(updateVaccine));
    }

    // Finds a vaccine by its ID.
    @Override
    public ResultData<VaccineResponse> findVaccineById(Long id) {
        // Find the vaccine by ID
        Vaccine vaccine = vaccineRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Vaccine with ID " + id + " not found."));

        // Return the vaccine response
        return ResultInfo.success(this.converter.toVaccineResponse(vaccine));
    }

    // Finds all vaccines.
    @Override
    public ResultData<List<VaccineResponse>> findAllVaccines() {
        // Get all vaccines from the repository
        List<Vaccine> allVaccines = this.vaccineRepository.findAll();

        // Convert vaccines to vaccine responses
        List<VaccineResponse> vaccineResponses = allVaccines.stream()
                .map(this.converter::toVaccineResponse)
                .collect(Collectors.toList());

        // Return the list of vaccine responses
        return ResultInfo.success(vaccineResponses);
    }

    // Deletes a vaccine by its ID.
    @Override
    public Result deleteVaccine(Long id) {
        // Find the vaccine by ID
        Vaccine vaccine = this.findVaccine(id);

        // Delete the vaccine from the repository
        this.vaccineRepository.delete(vaccine);

        // Return a successful result
        return ResultInfo.ok();
    }

    // Finds a vaccine by its ID.
    public Vaccine findVaccine(Long id){
        return this.vaccineRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Vaccine with ID " + id + " not found."));
    }

    // Finds vaccines by animal ID.
    @Override
    public ResultData<List<VaccineResponse>> findByAnimalId(Long animalId) {
        // Find the associated animal by ID
        this.animalManager.findAnimalById(animalId);

        // Find vaccines by animal ID
        List<Vaccine> vaccines = this.vaccineRepository.findByAnimalId(animalId);

        // Check if any vaccines are found
        if (vaccines.isEmpty()) {
            throw new EntityNotFoundException("No vaccines found for the animal with ID " + animalId);
        }

        // Convert vaccines to vaccine responses
        List<VaccineResponse> vaccineResponses = vaccines.stream()
                .map(this.converter::toVaccineResponse)
                .collect(Collectors.toList());

        // Return the list of vaccine responses
        return ResultInfo.success(vaccineResponses);
    }

    // Finds expiring vaccines within a date range.
    @Override
    public ResultData<List<VaccineResponse>> findExpiringVaccines(LocalDate startDate, LocalDate endDate) {
        // Find expiring vaccines within the specified date range
        List<Vaccine> expiringVaccines = vaccineRepository.findByProtectionFinishDateBetween(startDate, endDate);

        // Convert expiring vaccines to vaccine responses
        List<VaccineResponse> vaccineResponses = expiringVaccines.stream()
                .map(this.converter::toVaccineResponse)
                .collect(Collectors.toList());

        // Return the list of expiring vaccine responses
        return ResultInfo.success(vaccineResponses);
    }
}
