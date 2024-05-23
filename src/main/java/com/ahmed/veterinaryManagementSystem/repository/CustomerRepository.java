package com.ahmed.veterinaryManagementSystem.repository;

import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.response.CustomerResponse;
import com.ahmed.veterinaryManagementSystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The CustomerRepository interface provides CRUD operations for Customer entities.
 * It extends JpaRepository to inherit standard CRUD operations.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Finds a customer by their email address.
    boolean existsByMail(String mail);

    // Finds a customer by their phone number.
    boolean existsByPhone(String phone);

    // Finds customers by their name.
    List<Customer> findByName(String name);
}
