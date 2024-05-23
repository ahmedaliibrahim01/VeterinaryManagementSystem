package com.ahmed.veterinaryManagementSystem.repository;

import com.ahmed.veterinaryManagementSystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The AppointmentRepository interface provides access to the Appointment entity
 * for managing veterinary appointments.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Checks if an appointment exists for a given doctor at a specific date and time.
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentDateTime = :appointmentDateTime")
    boolean existsByDoctorIdAndAppointmentDateTime(@Param("doctorId") Long doctorId, @Param("appointmentDateTime") LocalDateTime appointmentDateTime);

    // Checks if an appointment exists for a given doctor within a specific date and time range.
    boolean existsByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    // Finds appointments for a given animal within a specific date and time range.
    List<Appointment> findByAnimalIdAndAppointmentDateTimeBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime endDateTime);

    // Finds appointments for a given doctor within a specific date and time range.
    List<Appointment> findByDoctorIdAndAppointmentDateTimeBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
