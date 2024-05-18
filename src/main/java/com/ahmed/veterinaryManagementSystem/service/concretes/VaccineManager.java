package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.mapper.VaccineMapper;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.VaccineResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Vaccine;
import com.ahmed.veterinaryManagementSystem.repository.VaccineRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.VaccineService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VaccineManager implements VaccineService {
    private final VaccineRepository vaccineRepository;
    private final AnimalManager animalManager;
    private final VaccineMapper mapper;

    public VaccineManager(VaccineRepository vaccineRepository, AnimalManager animalManager, VaccineMapper mapper) {
        this.vaccineRepository = vaccineRepository;
        this.animalManager = animalManager;
        this.mapper = mapper;
    }

    public ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest) {
        // Hayvanın varlığını kontrol edin
        AnimalResponse animalResponse = this.animalManager.findById(vaccineSaveRequest.getAnimalId()).getData();
        if (animalResponse == null) {
            throw new EntityNotFoundException("Animal with ID " + vaccineSaveRequest.getAnimalId() + " not found.");
        }

        // Aşıyı veritabanında kontrol edin
        List<Vaccine> existingVaccines = vaccineRepository.findByNameAndCodeAndProtectionDatesAndAnimalId(
                vaccineSaveRequest.getName(),
                vaccineSaveRequest.getCode(),
                vaccineSaveRequest.getProtectionStartDate(),
                vaccineSaveRequest.getProtectionFinishDate(),
                vaccineSaveRequest.getAnimalId()
        );

        // Eğer aşı varsa, hata fırlatın
        if (!existingVaccines.isEmpty()) {
            throw new IllegalArgumentException("A vaccine with the same name, code, start date, end date, and animal ID already exists.");
        } else {
            // Eğer yoksa, yeni aşıyı kaydedin
            Vaccine saveVaccine = this.mapper.saveVaccine(vaccineSaveRequest);
            this.vaccineRepository.save(saveVaccine);
            return ResultInfo.created(this.mapper.toVaccineResponse(saveVaccine));
        }
    }


    @Override
    public ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest) {
        Long vaccineId = vaccineUpdateRequest.getId();
        Vaccine existingVaccine = this.vaccineRepository.findById(vaccineId).orElseThrow(() -> new EntityNotFoundException("Vaccine with ID " + vaccineId + " not found."));

        Long animalId = vaccineUpdateRequest.getAnimalId();
        Animal existingAnimal = this.animalManager.findAnimalId(animalId);

        Vaccine updateVaccine = this.mapper.updateVaccine(vaccineUpdateRequest);
        this.vaccineRepository.save(updateVaccine);
        return ResultInfo.success(this.mapper.toVaccineResponse(updateVaccine));
    }

    @Override
    public ResultData<VaccineResponse> findById(Long id) {
        Vaccine vaccine = vaccineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vaccine with ID " + id + " not found."));
        return ResultInfo.success(this.mapper.toVaccineResponse(vaccine));
    }

    @Override
    public ResultData<List<VaccineResponse>> findAll() {
        List<Vaccine> allVaccines = this.vaccineRepository.findAll();
        List<VaccineResponse> vaccineResponses = allVaccines.stream().map(this.mapper::toVaccineResponse).collect(Collectors.toList());
        return ResultInfo.success(vaccineResponses);
    }

    @Override
    public Result delete(Long id) {
        Vaccine vaccine = this.vaccineRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vaccine with ID " + id + " not found."));
        this.vaccineRepository.delete(vaccine);
        return ResultInfo.ok();
    }

    @Override
    public ResultData<List<VaccineResponse>> findByAnimalId(Long animalId) {

        AnimalResponse animalResponse = this.animalManager.findById(animalId).getData();
        if (animalResponse == null) {
            throw new EntityNotFoundException("Animal with ID " + animalId + " not found.");
        }

        List<Vaccine> vaccines = this.vaccineRepository.findByAnimalId(animalId);
        if (vaccines.isEmpty()) {
            throw new EntityNotFoundException("No vaccines found for the animal with ID " + animalId);
        }

        List<VaccineResponse> vaccineResponses = vaccines.stream().map(this.mapper::toVaccineResponse).collect(Collectors.toList());
        return ResultInfo.success(vaccineResponses);
    }

    @Override
    public ResultData<List<VaccineResponse>> findExpiringVaccines(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> expiringVaccines = vaccineRepository.findByProtectionFinishDateBetween(startDate, endDate);
        List<VaccineResponse> vaccineResponses = expiringVaccines.stream()
                .map(this.mapper::toVaccineResponse)
                .collect(Collectors.toList());
        return ResultInfo.success(vaccineResponses);
    }
}
