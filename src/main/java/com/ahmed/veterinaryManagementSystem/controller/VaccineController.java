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

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {
    private final VaccineService vaccineService;

    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
        return this.vaccineService.save(vaccineSaveRequest);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return this.vaccineService.update(vaccineUpdateRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> findById(@PathVariable("id") Long id) {
        return this.vaccineService.findById(id);
    }

    @GetMapping()
    public ResultData<List<VaccineResponse>> findAll() {
        return this.vaccineService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        return this.vaccineService.delete(id);
    }

    @GetMapping("/{animalId}/vaccines")
    public ResultData<List<VaccineResponse>> findByCustomerId(@PathVariable("animalId") Long animalId){
        return this.vaccineService.findByAnimalId(animalId);
    }

    @GetMapping("/expiring")
    public ResultData<List<VaccineResponse>> findExpiringVaccines(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return this.vaccineService.findExpiringVaccines(startDate, endDate);
    }

}