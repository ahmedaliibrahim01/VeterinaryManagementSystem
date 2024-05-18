package com.ahmed.veterinaryManagementSystem.dto.mapper;

import com.ahmed.veterinaryManagementSystem.dto.request.AnimalSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Customer;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class AnimalMapper {

    private final CustomerRepository customerRepository;

    public AnimalMapper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Animal saveAnimal(AnimalSaveRequest animalSaveRequest) {
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

    public Animal updateAnimal(AnimalUpdateRequest animalUpdateRequest) {
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
