package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.VaccineUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.VaccineResponse;

import java.time.LocalDate;
import java.util.List;

public interface VaccineService {
    ResultData<VaccineResponse> save(VaccineSaveRequest vaccineSaveRequest);
    ResultData<VaccineResponse> update(VaccineUpdateRequest vaccineUpdateRequest);
    ResultData<VaccineResponse> findById(Long id);
    ResultData<List<VaccineResponse>> findAll ();
    Result delete(Long id);
    ResultData<List<VaccineResponse>> findByAnimalId(Long animalId);
    ResultData<List<VaccineResponse>> findExpiringVaccines(LocalDate startDate, LocalDate endDate);

}
