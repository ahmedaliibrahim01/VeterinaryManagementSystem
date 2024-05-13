package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    Customer findById(Long id);
    List<Customer> findAll();
    Customer update(Customer customer);
    void delete(Long id);
}
