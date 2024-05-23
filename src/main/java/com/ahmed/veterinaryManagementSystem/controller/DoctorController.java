package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.DoctorResponse;
import com.ahmed.veterinaryManagementSystem.service.abstracts.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The DoctorController class defines endpoints for managing doctor-related operations.
 */
@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    // Initializes a new instance of the DoctorController class with the specified service.
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Endpoint to save a new doctor.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> saveDoctor(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        return this.doctorService.saveDoctor(doctorSaveRequest);
    }

    // Endpoint to update an existing doctor.
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> updateDoctor(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        return this.doctorService.updateDoctor(doctorUpdateRequest);
    }

    // Endpoint to find a doctor by ID.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> findDoctorById(@PathVariable("id") Long id) {
        return this.doctorService.findDoctorById(id);
    }

    // Endpoint to find all doctors.
    @GetMapping()
    public ResultData<List<DoctorResponse>> findAllDoctors() {
        return this.doctorService.findAllDoctors();
    }

    // Endpoint to delete a doctor by ID.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteDoctor(@PathVariable("id") Long id) {
        return this.doctorService.deleteDoctor(id);
    }
}