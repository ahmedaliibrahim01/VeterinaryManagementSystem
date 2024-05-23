package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.CustomerResponse;

import java.util.List;

/**
 * The CustomerService interface defines methods to manage customer-related operations.
 */
public interface CustomerService {
    // Saves a new customer.
    ResultData<CustomerResponse> saveCustomer(CustomerSaveRequest customerSaveRequest);

    // Updates an existing customer.
    ResultData<CustomerResponse> updateCustomer(CustomerUpdateRequest customerUpdateRequest);

    // Finds a customer by their ID.
    ResultData<CustomerResponse> findCustomerById(Long id);

    // Finds customers by their name.
    ResultData<List<CustomerResponse>> findCustomerByName(String name);

    // Finds all customers.
    ResultData<List<CustomerResponse>> findAllCustomers();

    // Deletes a customer by their ID.
    Result deleteCustomer(Long id);
}
