package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.CustomerUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    ResultData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest);
    ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest);

    ResultData<CustomerResponse> findById(Long id);

    ResultData<List<CustomerResponse>> findByName(String name);

    ResultData<List<CustomerResponse>> findAll();

    Result delete(Long id);

}
