package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.model.Customer;
import com.ahmed.veterinaryManagementSystem.repository.CustomerRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        // Check if email already exists
        if (customerRepository.findByMail(customer.getMail()).isPresent()) {
            throw new IllegalArgumentException("Email address is already registered.");
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) {
        // Assuming the customer already exists in the database
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = findById(id);
        customerRepository.delete(customer);
    }
}