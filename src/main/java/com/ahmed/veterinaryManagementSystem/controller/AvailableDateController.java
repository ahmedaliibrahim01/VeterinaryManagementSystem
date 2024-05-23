package com.ahmed.veterinaryManagementSystem.controller;


import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.dto.response.AvailableDateResponse;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AvailableDateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The AvailableDateController class handles HTTP requests for managing available dates.
 */
@RestController
@RequestMapping("/v1/availableDates")
public class AvailableDateController {
    private final AvailableDateService availableDateService;

    // Constructs an instance of AvailableDateController.
    public AvailableDateController(AvailableDateService availableDateService) {
        this.availableDateService = availableDateService;
    }

    // Handles POST requests to save a new available date.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> saveAvailableDate(
            @Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        return this.availableDateService.saveAvailableDate(availableDateSaveRequest);
    }

    // Handles PUT requests to update an existing available date.
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> updateAvailableDate(
            @Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        return this.availableDateService.updateAvailableDate(availableDateUpdateRequest);
    }

    // Handles GET requests to find an available date by its ID.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> findAvailableDateById(@PathVariable("id") Long id) {
        return this.availableDateService.findAvailableDateById(id);
    }

    // Handles GET requests to fetch all available dates.
    @GetMapping()
    public ResultData<List<AvailableDateResponse>> findAll() {
        return this.availableDateService.findAllAvailableDates();
    }

    //Handles DELETE requests to delete an available date by its ID.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteAvailableDate(@PathVariable("id") Long id) {
        return this.availableDateService.deleteAvailableDate(id);
    }
}