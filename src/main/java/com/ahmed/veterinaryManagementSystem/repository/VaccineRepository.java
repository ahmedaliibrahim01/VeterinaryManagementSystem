package com.ahmed.veterinaryManagementSystem.repository;

import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * The VaccineRepository interface provides methods for accessing vaccine data from the database.
 * It extends JpaRepository which provides basic CRUD operations for the Vaccine entity.
 */
@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    // Finds vaccines by the ID of the animal to which they are administered.
    List<Vaccine> findByAnimalId(Long animalId);

    /**
     * Finds vaccines by their name, code, start and finish dates of protection, and the ID of the animal to which they are administered.
     * @param name The name of the vaccine.
     * @param code The code of the vaccine.
     * @param startDate The start date of protection.
     * @param finishDate The finish date of protection.
     * @param animalId The ID of the animal.
     * @return A list of vaccines that match the specified criteria.
     */
    @Query("SELECT v FROM Vaccine v WHERE v.name = :name AND v.code = :code AND v.protectionStartDate = :startDate AND v.protectionFinishDate = :finishDate AND v.animal.id = :animalId")
    List<Vaccine> findByNameAndCodeAndProtectionDatesAndAnimalId(@Param("name") String name, @Param("code") String code, @Param("startDate") LocalDate startDate, @Param("finishDate") LocalDate finishDate, @Param("animalId") Long animalId);

    // Finds vaccines by their name, code, and the ID of the animal to which they are administered.
    @Query("SELECT v FROM Vaccine v WHERE v.name = :name AND v.code = :code AND v.animal.id = :animalId")
    List<Vaccine> findByNameAndCodeAndAnimalId(@Param("name") String name, @Param("code") String code, @Param("animalId") Long animalId);

    // Finds vaccines whose protection finish dates fall between the specified start and end dates.
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}
