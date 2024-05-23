package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.dto.converter.AnimalConverter;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AnimalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the AnimalService interface providing functionalities for managing animals.
 */
@Service
public class AnimalManager implements AnimalService {
    // Dependencies
    private final AnimalRepository animalRepository;
    private final CustomerManager customerManager;
    private final AnimalConverter converter;

    //Constructor for AnimalManager class.
    public AnimalManager(AnimalRepository animalRepository, CustomerManager customerManager, AnimalConverter converter) {
        this.animalRepository = animalRepository;
        this.customerManager = customerManager;
        this.converter = converter;
    }

    // Saves a new animal.
    public ResultData<AnimalResponse> saveAnimal(AnimalSaveRequest animalSaveRequest) {
        // Check if the animal already exists
        checkIfAnimalExists(animalSaveRequest);
        // Find customer by customerId
        this.customerManager.findCustomerId(animalSaveRequest.getCustomerId());
        // Convert and save the animal
        Animal saveAnimal = this.converter.convertToAnimal(animalSaveRequest);
        this.animalRepository.save(saveAnimal);
        // Return success result with created animal data
        return ResultInfo.created(this.converter.toAnimalResponse(saveAnimal));
    }

    //Checks if an animal with the same properties already exists.
    private void checkIfAnimalExists(AnimalSaveRequest animalSaveRequest) {
        Animal existingAnimal = animalRepository.findByNameAndSpeciesAndBreedAndGenderAndColourAndDateOfBirthAndCustomerId(
                animalSaveRequest.getName(),
                animalSaveRequest.getSpecies(),
                animalSaveRequest.getBreed(),
                animalSaveRequest.getGender(),
                animalSaveRequest.getColour(),
                animalSaveRequest.getDateOfBirth(),
                animalSaveRequest.getCustomerId()
        );

        if (existingAnimal != null) {
            throw new IllegalArgumentException("Animal with same properties already exists.");
        }
    }
    // Updates an existing animal.
    @Override
    public ResultData<AnimalResponse> updateAnimal(AnimalUpdateRequest animalUpdateRequest) {
        // Find the animal by ID
        this.findAnimalById(animalUpdateRequest.getId());
        // Find customer by customerId
        this.customerManager.findCustomerId(animalUpdateRequest.getCustomerId());
        // Convert and save the updated animal
        Animal updateAnimal = this.converter.convertToUpdateAnimal(animalUpdateRequest);
        this.animalRepository.save(updateAnimal);
        // Return success result with updated animal data
        return ResultInfo.success(this.converter.toAnimalResponse(updateAnimal));
    }

    // Finds an animal by its ID.
    @Override
    public ResultData<AnimalResponse> findAnimalById(Long id) {
        // Find the animal by ID
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with ID: " + id));
        // Return success result with found animal data
        return ResultInfo.success(this.converter.toAnimalResponse(animal));
    }

    // Finds animals by their name.
    @Override
    public ResultData<List<AnimalResponse>> findAnimalByName(String name) {
        // Find animals by name
        List<Animal> animals = animalRepository.findByName(name);
        if (animals.isEmpty()) {
            throw new EntityNotFoundException("No animals found with name: " + name);
        }
        // Map animal entities to animal responses
        List<AnimalResponse> animalResponses = animals.stream()
                .map(this.converter::toAnimalResponse)
                .collect(Collectors.toList());
        // Return success result with found animals data
        return ResultInfo.success(animalResponses);
    }

    // Finds all animals.
    @Override
    public ResultData<List<AnimalResponse>> findAllAnimals() {
        // Find all animals
        List<Animal> allAnimals = this.animalRepository.findAll();
        // Map animal entities to animal responses
        List<AnimalResponse> animalResponses = allAnimals.stream()
                .map(this.converter::toAnimalResponse)
                .collect(Collectors.toList());
        // Return success result with all animals data
        return ResultInfo.success(animalResponses);
    }

    // Deletes an animal by its ID.
    @Override
    public Result deleteAnimal(Long id) {
        // Find the animal by ID
        Animal animal = this.findAnimal(id);
        // Delete the animal
        this.animalRepository.delete(animal);
        // Return success result
        return ResultInfo.ok();
    }

    // Finds animals by customer ID.
    @Override
    public ResultData<List<AnimalResponse>> findByCustomerId(Long customerId) {
        // Find customer by customerId
        this.customerManager.findCustomerId(customerId);
        // Find animals by customerId
        List<Animal> animals = this.animalRepository.findByCustomerId(customerId);
        if (animals.isEmpty()) {
            throw new EntityNotFoundException("No animals found for the customer with ID: " + customerId);
        }
        // Map animal entities to animal responses
        List<AnimalResponse> animalResponses = animals.stream()
                .map(this.converter::toAnimalResponse)
                .collect(Collectors.toList());
        // Return success result with found animals data
        return ResultInfo.success(animalResponses);
    }

    // Finds an animal by its ID.
    public Animal findAnimal(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with ID: " + id));
    }
}