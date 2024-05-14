package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.core.config.modelMapper.ModelMapperService;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.customer.CustomerResponse;
import com.ahmed.veterinaryManagementSystem.dto.customer.CustomerSaveRequest;
import com.ahmed.veterinaryManagementSystem.model.Customer;
import com.ahmed.veterinaryManagementSystem.service.abstracts.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    private final ModelMapperService modelMapper;

    public CustomerController(CustomerService customerService, ModelMapperService modelMapperService) {
        this.customerService = customerService;
        this.modelMapper = modelMapperService;
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerService.save(saveCustomer);
        return ResultInfo.created(this.modelMapper
                .forResponse()
                .map(saveCustomer, CustomerResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> findById(@PathVariable("id") Long id){
        Customer customer = this.customerService.findById(id);
        return ResultInfo.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    /*
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll() {
        return this.customerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findById(@PathVariable("id") Long id) {
        return this.customerService.findById(id);
    }

    @PutMapping()
    public Customer update(@RequestBody Customer customer) {
        return this.customerService.update(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
    }

     */

}