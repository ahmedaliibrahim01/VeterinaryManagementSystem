package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.model.Animal;
import com.ahmed.veterinaryManagementSystem.model.Customer;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AnimalService;
import com.ahmed.veterinaryManagementSystem.service.abstracts.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements AnimalService {
    private final AnimalRepository animalRepository;
    private final CustomerManager customerManager;

    public AnimalManager(AnimalRepository animalRepository, CustomerManager customerManager) {
        this.animalRepository = animalRepository;
        this.customerManager = customerManager;
    }

    @Override
    public Animal save(Animal animal) {
        Customer customer = customerManager.findById(animal.getCustomer().getId());
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found with id: " + animal.getCustomer().getId());
        }
        return animalRepository.save(animal);
    }

    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Animal not found with id: " + id));
    }

    @Override
    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    @Override
    public Animal update(Animal animal) {
        return animalRepository.save(animal);
    }

    @Override
    public void delete(Long id) {
        Animal animal = findById(id);
        animalRepository.delete(animal);
    }
}