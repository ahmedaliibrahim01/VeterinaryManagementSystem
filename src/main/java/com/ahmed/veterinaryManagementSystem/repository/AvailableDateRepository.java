package com.ahmed.veterinaryManagementSystem.repository;

import com.ahmed.veterinaryManagementSystem.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
/**
 * The AvailableDateRepository interface provides access to the AvailableDate entity
 * for managing veterinary appointments and available dates.
 */
@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
    // Finds an AvailableDate record by the doctor ID and the available date.
    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate availableDate);
}