package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.VaccineResponse;
import com.ahmed.veterinaryManagementSystem.service.abstracts.VaccineService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * The VaccineController class handles HTTP requests related to vaccine operations.
 */
@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {
    private final VaccineService vaccineService;

    // Initializes a new instance of the VaccineController class with the specified vaccine service.
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    // Handles HTTP POST requests to save a new vaccine.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> saveVaccine(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        return this.vaccineService.saveVaccine(vaccineSaveRequest);
    }

    // Handles HTTP PUT requests to update an existing vaccine.
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> updateVaccine(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return this.vaccineService.updateVaccine(vaccineUpdateRequest);
    }

    // Handles HTTP GET requests to find a vaccine by its ID.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> findVaccineById(@PathVariable("id") Long id) {
        return this.vaccineService.findVaccineById(id);
    }

    // Handles HTTP GET requests to find vaccines by animal ID.
    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> findByAnimalId(@PathVariable("id") Long animalId) {
        return this.vaccineService.findByAnimalId(animalId);
    }

    // Handles HTTP GET requests to find all vaccines.
    @GetMapping()
    public ResultData<List<VaccineResponse>> findAllVaccines() {
        return this.vaccineService.findAllVaccines();
    }

    // Handles HTTP DELETE requests to delete a vaccine by its ID.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteVaccine(@PathVariable("id") Long id) {
        return this.vaccineService.deleteVaccine(id);
    }

    // Handles HTTP GET requests to find expiring vaccines within a date range.
    @GetMapping("/expiring")
    public ResultData<List<VaccineResponse>> findExpiringVaccines(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return this.vaccineService.findExpiringVaccines(startDate, endDate);
    }
}
