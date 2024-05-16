package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.model.Animal;
import com.ahmed.veterinaryManagementSystem.model.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    void save(Customer customer);

    Customer update(Customer customer);

    Customer findById(Long id);

    List<Customer> findByName(String name);

    Page<Customer> cursor(int page, int pageSize);

    void delete(Long id);

}
