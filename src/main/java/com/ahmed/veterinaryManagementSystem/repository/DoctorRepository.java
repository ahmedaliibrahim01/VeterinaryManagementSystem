package com.ahmed.veterinaryManagementSystem.repository;

import com.ahmed.veterinaryManagementSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * The DoctorRepository interface provides access to Doctor entities in the database.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Finds a doctor by email address.
    Optional<Object> findByMail(String mail);
    // Finds a doctor by email address.
    Optional<Object> findByPhone(String mail);
}