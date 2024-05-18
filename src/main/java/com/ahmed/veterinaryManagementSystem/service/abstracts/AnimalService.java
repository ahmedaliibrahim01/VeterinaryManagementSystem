package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AnimalUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;

import java.util.List;

public interface AnimalService {
    ResultData<AnimalResponse> save(AnimalSaveRequest animalSaveRequest);

    ResultData<AnimalResponse> update(AnimalUpdateRequest animalUpdateRequest);

    ResultData<AnimalResponse> findById(Long id);

    ResultData<List<AnimalResponse>> findByName(String name);

    ResultData<List<AnimalResponse>> findAll ();

    Result delete(Long id);
    ResultData<List<AnimalResponse>> findByCustomerId(Long customerId);
}
