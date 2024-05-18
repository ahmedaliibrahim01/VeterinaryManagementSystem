package com.ahmed.veterinaryManagementSystem.dto.mapper;

import com.ahmed.veterinaryManagementSystem.dto.request.VaccineSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.VaccineResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Vaccine;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import org.springframework.stereotype.Component;

@Component
public class VaccineMapper {
    private final AnimalRepository animalRepository;

    public VaccineMapper(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Vaccine saveVaccine(VaccineSaveRequest vaccineSaveRequest) {
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
    public Vaccine updateVaccine(VaccineUpdateRequest vaccineUpdateRequest){
        if(vaccineUpdateRequest == null){
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