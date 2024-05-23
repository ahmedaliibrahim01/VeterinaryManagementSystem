package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.converter.CustomerConverter;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.CustomerResponse;
import com.ahmed.veterinaryManagementSystem.entity.Customer;
import com.ahmed.veterinaryManagementSystem.repository.CustomerRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The CustomerManager class implements the CustomerService interface and provides methods
 * to manage customer-related operations.
 */
@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    // Initializes a new instance of the CustomerManager class with the specified repository and converter.
    public CustomerManager(CustomerRepository customerRepository, CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.customerConverter = customerConverter;
    }

    @Override
    public ResultData<CustomerResponse> saveCustomer(CustomerSaveRequest customerSaveRequest) {
        // Convert the request DTO to an entity
        Customer saveCustomer = this.customerConverter.convertToCustomer(customerSaveRequest);

        // Check if the saveCustomer already exists
        checkCustomerExistence(saveCustomer);

        // Save the saveCustomer entity
        this.customerRepository.save(saveCustomer);

        // Return the created saveCustomer response
        return ResultInfo.success(this.customerConverter.toCustomerResponse(saveCustomer));
    }


    @Override
    public ResultData<CustomerResponse> updateCustomer(CustomerUpdateRequest customerUpdateRequest) {
        // Find the existing customer by ID
        findCustomerById(customerUpdateRequest.getId());

        // Convert the request DTO to an updated customer entity
        Customer updatedCustomer = this.customerConverter.convertToUpdatedCustomer(customerUpdateRequest);

        // Set the ID of the updated customer
        updatedCustomer.setId(customerUpdateRequest.getId());

        // Save the updated customer entity
        this.customerRepository.save(updatedCustomer);

        // Return the updated customer response
        return ResultInfo.success(this.customerConverter.toCustomerResponse(updatedCustomer));
    }


    // Finds a customer by their ID.
    public Customer findCustomerId(Long customerId) {
        return this.customerRepository.findById(customerId).orElseThrow(()
                -> new EntityNotFoundException("Customer with ID " + customerId + " not found"));
    }

    @Override
    public ResultData<CustomerResponse> findCustomerById(Long id) {
        Customer customer = findCustomerId(id);
        return ResultInfo.success(this.customerConverter.toCustomerResponse(customer));
    }

    @Override
    public ResultData<List<CustomerResponse>> findCustomerByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        if (customers.isEmpty()) {
            throw new EntityNotFoundException("Customer with name " + name + " not found.");
        }
        List<CustomerResponse> customerResponses = customers.stream()
                .map(this.customerConverter::toCustomerResponse).collect(Collectors.toList());
        return ResultInfo.success(customerResponses);
    }

    @Override
    public ResultData<List<CustomerResponse>> findAllCustomers() {
        List<Customer> allCustomers = this.customerRepository.findAll();
        List<CustomerResponse> customerResponses = allCustomers.stream()
                .map(this.customerConverter::toCustomerResponse).collect(Collectors.toList());
        return ResultInfo.success(customerResponses);
    }

    @Override
    public Result deleteCustomer(Long id) {
        Customer customer = findCustomerId(id);
        this.customerRepository.delete(customer);
        return ResultInfo.ok();
    }

    // Checks if a customer already exists based on their email and phone.
    private void checkCustomerExistence(Customer customer) {
        if (customerRepository.existsByMail(customer.getMail())) {
            throw new IllegalArgumentException("Email address " + customer.getMail() + " is already registered.");
        }
        if (customerRepository.existsByPhone(customer.getPhone())) {
            throw new IllegalArgumentException("Phone " + customer.getPhone() + " is already registered.");
        }
    }
}
