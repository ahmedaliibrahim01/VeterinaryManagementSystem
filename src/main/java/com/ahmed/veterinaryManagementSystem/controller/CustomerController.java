package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.core.config.modelMapper.ModelMapperService;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.customer.CustomerUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.CursorResponse;
import com.ahmed.veterinaryManagementSystem.dto.response.animal.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.dto.response.customer.CustomerResponse;
import com.ahmed.veterinaryManagementSystem.dto.request.customer.CustomerSaveRequest;
import com.ahmed.veterinaryManagementSystem.model.Animal;
import com.ahmed.veterinaryManagementSystem.model.Customer;
import com.ahmed.veterinaryManagementSystem.service.abstracts.CustomerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResultData<CustomerResponse> findById(@PathVariable("id") Long id) {
        Customer customer = this.customerService.findById(id);
        return ResultInfo.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @GetMapping("/name/{name}")
    public ResultData<List<CustomerResponse>> findByName(@PathVariable("name") String name) {
        List<Customer> customers = this.customerService.findByName(name);
        List<CustomerResponse> customerResponses = customers.stream()
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class))
                .collect(Collectors.toList());
        return ResultInfo.success(customerResponses);
    }

    @GetMapping()
    public ResultData<CursorResponse<CustomerResponse>> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
        return ResultInfo.cursor(customerResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        Customer updatedCustomer = modelMapper.forRequest()
                .map(customerUpdateRequest, Customer.class);
        return ResultInfo.success(modelMapper.forResponse()
                .map(customerService.update(updatedCustomer), CustomerResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultInfo.ok();
    }
}