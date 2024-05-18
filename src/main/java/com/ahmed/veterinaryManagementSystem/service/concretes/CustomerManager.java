package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.mapper.CustomerMapper;
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


@Service
public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerManager(CustomerRepository customerRepository, CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }
    @Override
    public ResultData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.mapper.saveCustomer(customerSaveRequest);
        if (customerRepository.findByMail(saveCustomer.getMail()).isPresent()) {
            throw new IllegalArgumentException("Email address " + saveCustomer.getMail() + " is already registered.");
        }
        if (customerRepository.findByPhone(saveCustomer.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Phone " + saveCustomer.getPhone() + " is already registered.");
        }
        this.customerRepository.save(saveCustomer);
        return ResultInfo.created(this.mapper.toCustomerResponse(saveCustomer));
    }

    @Override
    public ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest) {
        Long customerId = customerUpdateRequest.getId();
        Customer existingCustomer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found."));

        Customer updatedCustomer = this.mapper.updateCustomer(customerUpdateRequest);
        updatedCustomer.setId(customerId);
        this.customerRepository.save(updatedCustomer);

        return ResultInfo.success(this.mapper.toCustomerResponse(updatedCustomer));
    }

    @Override
    public ResultData<CustomerResponse> findById(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + id + " not found."));
        return ResultInfo.success(this.mapper.toCustomerResponse(customer));
    }

    @Override
    public ResultData<List<CustomerResponse>> findByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        if (customers.isEmpty()) {
            throw new EntityNotFoundException("Customer with name " + name + " not found.");
        }
        List<CustomerResponse> customerResponses = customers.stream()
                .map(this.mapper::toCustomerResponse)
                .collect(Collectors.toList());
        return ResultInfo.success(customerResponses);
    }

    @Override
    public ResultData<List<CustomerResponse>> findAll() {
        List<Customer> allCustomers = this.customerRepository.findAll();
        List<CustomerResponse> customerResponses = allCustomers.stream()
                .map(this.mapper::toCustomerResponse)
                .collect(Collectors.toList());
        return ResultInfo.success(customerResponses);
    }

    @Override
    public Result delete(Long id) {
        Customer customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + id + " not found."));
        this.customerRepository.delete(customer);
        return ResultInfo.ok();
    }

    public Customer findCustomerId(Long customerId){
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer with ID " + customerId + " not found"));
    }
}
