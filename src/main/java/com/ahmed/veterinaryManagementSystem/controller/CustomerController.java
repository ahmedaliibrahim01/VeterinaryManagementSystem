package com.ahmed.veterinaryManagementSystem.controller;

import com.ahmed.veterinaryManagementSystem.model.Customer;
import com.ahmed.veterinaryManagementSystem.service.abstracts.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@RequestBody Customer customer) {
        return this.customerService.save(customer);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer findById(@PathVariable("id") Long id) {
        return this.customerService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll() {
        return this.customerService.findAll();
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

}