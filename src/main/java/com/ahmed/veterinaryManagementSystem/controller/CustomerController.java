package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.CustomerResponse;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerSaveRequest;
import com.ahmed.veterinaryManagementSystem.service.abstracts.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The CustomerController class defines REST endpoints for managing customer data.
 */
@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    // Initializes a new instance of the CustomerController class with the specified service.
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Creates a new customer.
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> saveCustomer(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        return this.customerService.saveCustomer(customerSaveRequest);
    }

    // Updates an existing customer.
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> updateCustomer(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return this.customerService.updateCustomer(customerUpdateRequest);
    }

    // Finds a customer by their ID.
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> findCustomerById(@PathVariable("id") Long id) {
        return this.customerService.findCustomerById(id);
    }

    // Finds customers by their name.
    @GetMapping("/name/{name}")
    public ResultData<List<CustomerResponse>> findCustomerByName(@PathVariable("name") String name) {
        return this.customerService.findCustomerByName(name);
    }

    // Finds all customers.
    @GetMapping()
    public ResultData<List<CustomerResponse>> findAllCustomers() {
        return this.customerService.findAllCustomers();
    }

    // Deletes a customer by their ID.
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result deleteCustomer(@PathVariable("id") Long id) {
        return this.customerService.deleteCustomer(id);
    }
}