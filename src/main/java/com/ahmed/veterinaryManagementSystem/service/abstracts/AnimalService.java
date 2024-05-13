package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.model.Animal;

import java.util.List;

public interface AnimalService {
    Animal save(Animal animal);
    Animal findById(Long id);
    List<Animal> findAll();
    Animal update(Animal animal);
    void delete(Long id);
}
