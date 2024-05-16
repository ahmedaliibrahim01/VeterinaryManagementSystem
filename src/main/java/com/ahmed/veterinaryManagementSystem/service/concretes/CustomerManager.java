package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.model.Customer;
import com.ahmed.veterinaryManagementSystem.repository.CustomerRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void save(Customer customer) {
            if (customerRepository.findByMail(customer.getMail()).isPresent()) {
                throw new IllegalArgumentException("Email address is already registered.");
            }
        this.customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found."));
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        if (customers.isEmpty()) {
            throw new EntityNotFoundException("Customer not found");
        }
        return customers;
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
        Customer existingCustomer = this.findById(customer.getId());
        if (customer.getMail() == null) {
            customer.setMail(existingCustomer.getMail());
        }
        return this.customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = findById(id);
        this.customerRepository.delete(customer);
    }
}