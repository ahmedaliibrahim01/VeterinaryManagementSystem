package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;

import java.util.List;

/**
 * The AnimalService interface defines the contract for managing animal-related operations in the veterinary management system.
 * It includes methods for saving, updating, finding by various criteria, and deleting animals.
 */
public interface AnimalService {

    // Saves a new animal
    ResultData<AnimalResponse> saveAnimal(AnimalSaveRequest animalSaveRequest);

    // Updates an existing animal
    ResultData<AnimalResponse> updateAnimal(AnimalUpdateRequest animalUpdateRequest);

    // Finds an animal by its ID
    ResultData<AnimalResponse> findAnimalById(Long id);

    // Finds animals by their name
    ResultData<List<AnimalResponse>> findAnimalByName(String name);

    // Retrieves all animals
    ResultData<List<AnimalResponse>> findAllAnimals();

    // Deletes an animal by its ID
    Result deleteAnimal(Long id);

    // Finds animals by customer ID
    ResultData<List<AnimalResponse>> findByCustomerId(Long customerId);
}