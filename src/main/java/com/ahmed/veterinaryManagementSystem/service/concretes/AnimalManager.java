package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.model.Animal;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AnimalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalManager(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void save(Animal animal) {
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
        return this.animalRepository.save(animal);
    }

    @Override
    public void delete(Long id) {
        Animal animal = findById(id);
        this.animalRepository.delete(animal);
    }
}