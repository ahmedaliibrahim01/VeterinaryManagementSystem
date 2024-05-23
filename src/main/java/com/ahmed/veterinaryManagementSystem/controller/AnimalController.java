package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AnimalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {
    private final AnimalService animalService;

    // Constructor of the AnimalController class
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // POST request to save a new animal
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> saveAnimal(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return this.animalService.saveAnimal(animalSaveRequest);
    }

    // PUT request to update an existing animal
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> updateAnimal(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        return this.animalService.updateAnimal(animalUpdateRequest);
    }

    // GET request to find animals by name
    @GetMapping("/name/{animalName}")
    public ResultData<List<AnimalResponse>> findAnimalByName(@PathVariable("animalName") String name) {
        return this.animalService.findAnimalByName(name);
    }

    // GET request to fetch all animals
    @GetMapping()
    public ResultData<List<AnimalResponse>> findAllAnimals() {
        return this.animalService.findAllAnimals();
    }

    // DELETE request to delete an animal
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteAnimal(@PathVariable("id") Long id) {
        return this.animalService.deleteAnimal(id);
    }

    // GET request to find animals by customer ID
    @GetMapping("/customer/{customerId}")
    public ResultData<List<AnimalResponse>> findByCustomerId(@PathVariable("customerId") Long customerId) {
        return this.animalService.findByCustomerId(customerId);
    }

    // GET request to find an animal by ID
    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> findAnimalById(@PathVariable("id") Long id) {
        return this.animalService.findAnimalById(id);
    }
}