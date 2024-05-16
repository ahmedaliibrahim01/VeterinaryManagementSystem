package com.ahmed.veterinaryManagementSystem.repository;

import com.ahmed.veterinaryManagementSystem.model.Animal;
import com.ahmed.veterinaryManagementSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByName(String name);
}
