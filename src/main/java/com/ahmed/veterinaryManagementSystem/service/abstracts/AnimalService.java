package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.model.Animal;
import com.ahmed.veterinaryManagementSystem.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnimalService {
    void save(Animal animal);

    Animal update(Animal animal);

    Animal findById(Long id);

    List<Animal> findByName(String name);

    Page<Animal> cursor(int page, int pageSize);

    void delete(Long id);
    List<Animal> findByOwnerId(Long ownerId);

}
