package com.ahmed.veterinaryManagementSystem.repository;

import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByName(String name);
    List<Animal> findByCustomerId(Long customerId);
    Optional<Animal> findByNameAndSpeciesAndBreedAndGenderAndColourAndDateOfBirthAndCustomerId(
            String name, String species, String breed, String gender, String colour, LocalDate dateOfBirth, Long customerId);

}
