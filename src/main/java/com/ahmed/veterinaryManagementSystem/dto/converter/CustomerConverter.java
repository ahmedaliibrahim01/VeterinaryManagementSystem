package com.ahmed.veterinaryManagementSystem.dto.converter;

import com.ahmed.veterinaryManagementSystem.dto.request.CustomerSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.CustomerResponse;
import com.ahmed.veterinaryManagementSystem.entity.Customer;
import org.springframework.stereotype.Component;
/**
 * The CustomerConverter class provides methods to convert between
 * Customer entities and their corresponding request and response DTOs.
 */
@Component
public class CustomerConverter {
    /**
     * Converts a CustomerSaveRequest object to a Customer entity.
     * @param customerSaveRequest the request object containing customer details to be saved.
     * @return a Customer entity populated with the provided details, or null if the request object is null.
     */
    public Customer convertToCustomer(CustomerSaveRequest customerSaveRequest) {
        if (customerSaveRequest == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setName(customerSaveRequest.getName());
        customer.setPhone(customerSaveRequest.getPhone());
        customer.setMail(customerSaveRequest.getMail());
        customer.setAddress(customerSaveRequest.getAddress());
        customer.setCity(customerSaveRequest.getCity());
        return customer;
    }
    /**
     * Converts a CustomerUpdateRequest object to a Customer entity.
     * @param customerUpdateRequest the request object containing customer details to be updated.
     * @return a Customer entity populated with the provided details, or null if the request object is null.
     */
    public Customer convertToUpdatedCustomer(CustomerUpdateRequest customerUpdateRequest) {
        if (customerUpdateRequest == null) {
            return null;
        }
        Customer customer = new Customer();
        customer.setId(customerUpdateRequest.getId());
        customer.setName(customerUpdateRequest.getName());
        customer.setPhone(customerUpdateRequest.getPhone());
        customer.setMail(customerUpdateRequest.getMail());
        customer.setAddress(customerUpdateRequest.getAddress());
        customer.setCity(customerUpdateRequest.getCity());
        return customer;
    }
    /**
     * Converts a Customer entity to a CustomerResponse object.
     * @param customer the Customer entity to be converted.
     * @return a CustomerResponse object populated with the customer's details, or null if the entity is null.
     */
    public CustomerResponse toCustomerResponse(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId());
        response.setName(customer.getName());
        response.setPhone(customer.getPhone());
        response.setMail(customer.getMail());
        response.setAddress(customer.getAddress());
        response.setCity(customer.getCity());
        return response;
    }
}
