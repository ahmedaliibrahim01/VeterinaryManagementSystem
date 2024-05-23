package com.ahmed.veterinaryManagementSystem.repository;

import com.ahmed.veterinaryManagementSystem.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
/**
 * The AnimalRepository interface provides CRUD operations for managing animal entities in the veterinary management system.
 * It extends the JpaRepository interface, which provides standard CRUD operations for interacting with the underlying database.
 * Additionally, it includes custom methods for specific queries such as finding an animal by its name, customer ID, or specific attributes.
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    // Finds animals by their name
    List<Animal> findByName(String name);

    // Finds animals by customer ID
    List<Animal> findByCustomerId(Long customerId);

    // Finds an animal by specific attributes
    Animal findByNameAndSpeciesAndBreedAndGenderAndColourAndDateOfBirthAndCustomerId(
            String name,
            String species,
            String breed,
            String gender,
            String colour,
            LocalDate dateOfBirth,
            Long customerId
    );
}
