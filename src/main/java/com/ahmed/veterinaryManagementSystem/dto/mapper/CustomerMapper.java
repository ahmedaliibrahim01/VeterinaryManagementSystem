package com.ahmed.veterinaryManagementSystem.dto.mapper;

import com.ahmed.veterinaryManagementSystem.dto.request.CustomerSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.CustomerResponse;
import com.ahmed.veterinaryManagementSystem.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer saveCustomer(CustomerSaveRequest customerSaveRequest) {
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

    public Customer updateCustomer(CustomerUpdateRequest customerUpdateRequest) {
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
