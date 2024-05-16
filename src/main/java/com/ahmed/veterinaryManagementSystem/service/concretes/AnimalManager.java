package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.request.animal.AnimalSaveRequest;
import com.ahmed.veterinaryManagementSystem.model.Animal;
import com.ahmed.veterinaryManagementSystem.model.Customer;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.repository.CustomerRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AnimalService;
import com.ahmed.veterinaryManagementSystem.service.abstracts.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalManager implements AnimalService {

    private final AnimalRepository animalRepository;
    private final CustomerManager customerManager;

    public AnimalManager(AnimalRepository animalRepository, CustomerManager customerManager) {
        this.animalRepository = animalRepository;
        this.customerManager = customerManager;
    }

    @Override
    public void save(Animal animal) {
        this.customerManager.findById(animal.getCustomer().getId());
        this.animalRepository.save(animal);
    }


    @Override
    public Animal findById(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Animal not found."));
    }

    @Override
    public List<Animal> findByName(String name) {
        List<Animal> animals = animalRepository.findByName(name);
        if (animals.isEmpty()) {
            throw new EntityNotFoundException("Animal not found");
        }
        return animals;
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepository.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        this.findById(animal.getId());
        this.customerManager.findById(animal.getCustomer().getId());
        return this.animalRepository.save(animal);
    }

    @Override
    public void delete(Long id) {
        Animal animal = findById(id);
        this.animalRepository.delete(animal);
    }

    @Override
    public List<Animal> findByOwnerId(Long ownerId) {
        // AnimalRepository kullanarak hayvanları sahip ID'sine göre bul
        List<Animal> animals = animalRepository.findByCustomer_Id(ownerId);
        // Bulunan hayvanları döndür
        return animals;
    }
}