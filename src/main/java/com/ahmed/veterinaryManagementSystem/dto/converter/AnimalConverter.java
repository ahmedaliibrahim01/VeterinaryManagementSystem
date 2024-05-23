package com.ahmed.veterinaryManagementSystem.dto.converter;

import com.ahmed.veterinaryManagementSystem.dto.request.AnimalSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Customer;
import com.ahmed.veterinaryManagementSystem.repository.CustomerRepository;
import org.springframework.stereotype.Component;

/**
 * The AnimalConverter class provides methods to convert between different representations of animal data.
 * It includes methods for converting AnimalSaveRequest and AnimalUpdateRequest objects to Animal entities,
 * as well as converting Animal entities to AnimalResponse objects.
 */
@Component
public class AnimalConverter {

    private final CustomerRepository customerRepository;

    public AnimalConverter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Converts an AnimalSaveRequest object to an Animal entity.
     * @param animalSaveRequest The AnimalSaveRequest object to convert.
     * @return The converted Animal entity.
     */
    public Animal convertToAnimal(AnimalSaveRequest animalSaveRequest) {
        if (animalSaveRequest == null) {
            return null;
        }
        Animal animal = new Animal();
        animal.setName(animalSaveRequest.getName());
        animal.setSpecies(animalSaveRequest.getSpecies());
        animal.setBreed(animalSaveRequest.getBreed());
        animal.setGender(animalSaveRequest.getGender());
        animal.setColour(animalSaveRequest.getColour());
        animal.setDateOfBirth(animalSaveRequest.getDateOfBirth());
        Customer customer = customerRepository.findById(animalSaveRequest.getCustomerId()).get();
        animal.setCustomer(customer);
        return animal;
    }

    /**
     * Converts an AnimalUpdateRequest object to an Animal entity.
     * @param animalUpdateRequest The AnimalUpdateRequest object to convert.
     * @return The converted Animal entity.
     */
    public Animal convertToUpdateAnimal(AnimalUpdateRequest animalUpdateRequest) {
        if (animalUpdateRequest == null) {
            return null;
        }
        Animal animal = new Animal();
        animal.setId(animalUpdateRequest.getId());
        animal.setName(animalUpdateRequest.getName());
        animal.setSpecies(animalUpdateRequest.getSpecies());
        animal.setBreed(animalUpdateRequest.getBreed());
        animal.setGender(animalUpdateRequest.getGender());
        animal.setColour(animalUpdateRequest.getColour());
        animal.setDateOfBirth(animalUpdateRequest.getDateOfBirth());
        Customer customer = customerRepository.findById(animalUpdateRequest.getCustomerId()).get();
        animal.setCustomer(customer);
        return animal;
    }

    /**
     * Converts an Animal entity to an AnimalResponse object.
     * @param animal The Animal entity to convert.
     * @return The converted AnimalResponse object.
     */
    public AnimalResponse toAnimalResponse(Animal animal) {
        if (animal == null) {
            return null;
        }
        AnimalResponse response = new AnimalResponse();
        response.setId(animal.getId());
        response.setName(animal.getName());
        response.setSpecies(animal.getSpecies());
        response.setBreed(animal.getBreed());
        response.setGender(animal.getGender());
        response.setColour(animal.getColour());
        response.setDateOfBirth(animal.getDateOfBirth());
        response.setCustomerId(animal.getCustomer().getId());
        return response;
    }
}
