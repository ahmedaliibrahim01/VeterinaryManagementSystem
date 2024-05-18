package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.mapper.AnimalMapper;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.dto.response.CustomerResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Customer;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AnimalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalManager implements AnimalService {
    private final AnimalRepository animalRepository;
    private final CustomerManager customerManager;
    private final AnimalMapper mapper;

    public AnimalManager(AnimalRepository animalRepository, CustomerManager customerManager, AnimalMapper mapper) {
        this.animalRepository = animalRepository;
        this.customerManager = customerManager;
        this.mapper = mapper;
    }


    public ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest) {
        // Check if animal with same properties already exists
        Optional<Animal> existingAnimalOptional = animalRepository.findByNameAndSpeciesAndBreedAndGenderAndColourAndDateOfBirthAndCustomerId(
                animalSaveRequest.getName(), animalSaveRequest.getSpecies(), animalSaveRequest.getBreed(),
                animalSaveRequest.getGender(), animalSaveRequest.getColour(), animalSaveRequest.getDateOfBirth(),
                animalSaveRequest.getCustomerId());
        if (existingAnimalOptional.isPresent()) {
            throw new IllegalArgumentException("Animal with same properties already exists.");
        }
        CustomerResponse customerResponse = this.customerManager
                .findById(animalSaveRequest.getCustomerId()).getData();
        if (customerResponse == null) {
            throw new EntityNotFoundException("Customer not found.");
        }
        Animal saveAnimal = this.mapper.saveAnimal(animalSaveRequest);
        this.animalRepository.save(saveAnimal);
        return ResultInfo.created(this.mapper.toAnimalResponse(saveAnimal));
    }

    @Override
    public ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest) {
        Long animalId = animalUpdateRequest.getId();
        Animal existingAnimal = this.animalRepository.findById(animalId)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with ID: " + animalId));

        Long customerId = animalUpdateRequest.getCustomerId();
        Customer existingCustomer = this.customerManager.findCustomerId(customerId);

        Animal updateAnimal = this.mapper.updateAnimal(animalUpdateRequest);
        this.animalRepository.save(updateAnimal);
        return ResultInfo.success(this.mapper.toAnimalResponse(updateAnimal));
    }

    @Override
    public ResultData<AnimalResponse> findById(Long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with ID: " + id));
        return ResultInfo.success(this.mapper.toAnimalResponse(animal));
    }

    @Override
    public ResultData<List<AnimalResponse>> findByName(String name) {
        List<Animal> animals = animalRepository.findByName(name);
        if (animals.isEmpty()) {
            throw new EntityNotFoundException("No animals found with name: " + name);
        }
        List<AnimalResponse> animalResponses = animals.stream()
                .map(this.mapper::toAnimalResponse)
                .collect(Collectors.toList());
        return ResultInfo.success(animalResponses);
    }

    @Override
    public ResultData<List<AnimalResponse>> findAll() {
        List<Animal> allAnimals = this.animalRepository.findAll();
        List<AnimalResponse> animalResponses = allAnimals.stream()
                .map(this.mapper::toAnimalResponse)
                .collect(Collectors.toList());
        return ResultInfo.success(animalResponses);
    }

    @Override
    public Result delete(Long id) {
        Animal animal = this.animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with ID: " + id));
        this.animalRepository.delete(animal);
        return ResultInfo.ok();
    }

    @Override
    public ResultData<List<AnimalResponse>> findByCustomerId(Long customerId) {
        // Find customer by customerId
        CustomerResponse customerResponse = this.customerManager.findById(customerId).getData();
        if (customerResponse == null) {
            throw new EntityNotFoundException("Customer not found with ID: " + customerId);
        }

        // Find animals by customerId
        List<Animal> animals = this.animalRepository.findByCustomerId(customerId);
        if (animals.isEmpty()) {
            throw new EntityNotFoundException("No animals found for the customer with ID: " + customerId);
        }

        // Map animal entities to animal responses
        List<AnimalResponse> animalResponses = animals.stream()
                .map(this.mapper::toAnimalResponse)
                .collect(Collectors.toList());

        return ResultInfo.success(animalResponses);
    }
    public Animal findAnimalId(Long id){
        return this.animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found with ID: " + id));
    }
}
