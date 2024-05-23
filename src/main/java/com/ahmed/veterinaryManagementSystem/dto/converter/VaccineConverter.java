package com.ahmed.veterinaryManagementSystem.dto.converter;

import com.ahmed.veterinaryManagementSystem.dto.request.VaccineSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.VaccineResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Vaccine;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import org.springframework.stereotype.Component;

/**
 * The VaccineConverter class provides methods to convert between different representations of vaccine data.
 * It includes methods for converting VaccineSaveRequest and VaccineUpdateRequest objects to Vaccine entities,
 * as well as converting Vaccine entities to VaccineResponse objects.
 */
@Component
public class VaccineConverter {
    private final AnimalRepository animalRepository;

    public VaccineConverter(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    /**
     * Converts a VaccineSaveRequest object to a Vaccine entity.
     * @param vaccineSaveRequest The VaccineSaveRequest object to convert.
     * @return The converted Vaccine entity.
     */
    public Vaccine convertToVaccine(VaccineSaveRequest vaccineSaveRequest) {
        if (vaccineSaveRequest == null) {
            return null;
        }
        Vaccine vaccine = new Vaccine();
        vaccine.setName(vaccineSaveRequest.getName());
        vaccine.setCode(vaccineSaveRequest.getCode());
        vaccine.setProtectionStartDate(vaccineSaveRequest.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineSaveRequest.getProtectionFinishDate());
        Animal animal = this.animalRepository.findById(vaccineSaveRequest.getAnimalId()).get();
        vaccine.setAnimal(animal);
        return vaccine;
    }

    /**
     * Converts a VaccineUpdateRequest object to a Vaccine entity.
     * @param vaccineUpdateRequest The VaccineUpdateRequest object to convert.
     * @return The converted Vaccine entity.
     */
    public Vaccine convertToUpdateVaccine(VaccineUpdateRequest vaccineUpdateRequest) {
        if (vaccineUpdateRequest == null) {
            return null;
        }
        Vaccine vaccine = new Vaccine();
        vaccine.setId(vaccineUpdateRequest.getId());
        vaccine.setName(vaccineUpdateRequest.getName());
        vaccine.setCode(vaccineUpdateRequest.getCode());
        vaccine.setProtectionStartDate(vaccineUpdateRequest.getProtectionStartDate());
        vaccine.setProtectionFinishDate(vaccineUpdateRequest.getProtectionFinishDate());
        Animal animal = animalRepository.findById(vaccineUpdateRequest.getAnimalId()).get();
        vaccine.setAnimal(animal);
        return vaccine;
    }

    /**
     * Converts a Vaccine entity to a VaccineResponse object.
     * @param vaccine The Vaccine entity to convert.
     * @return The converted VaccineResponse object.
     */
    public VaccineResponse toVaccineResponse(Vaccine vaccine) {
        if (vaccine == null) {
            return null;
        }
        VaccineResponse response = new VaccineResponse();
        response.setId(vaccine.getId());
        response.setName(vaccine.getName());
        response.setCode(vaccine.getCode());
        response.setProtectionStartDate(vaccine.getProtectionStartDate());
        response.setProtectionFinishDate(vaccine.getProtectionFinishDate());
        response.setAnimalId(vaccine.getAnimal().getId());
        return response;
    }
}
