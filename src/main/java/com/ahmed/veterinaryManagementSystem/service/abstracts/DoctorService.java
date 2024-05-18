package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.DoctorResponse;

public interface DoctorService {
    ResultData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest);
    ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest);
    ResultData<DoctorResponse> findById(Long id);
    Result delete(Long id);
}
