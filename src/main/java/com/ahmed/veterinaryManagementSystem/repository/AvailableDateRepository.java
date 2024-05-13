package com.ahmed.veterinaryManagementSystem.repository;

import com.ahmed.veterinaryManagementSystem.model.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {

}
