package com.ahmed.veterinaryManagementSystem.service.abstracts;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AppointmentResponse;
import com.ahmed.veterinaryManagementSystem.entity.Appointment;
import com.ahmed.veterinaryManagementSystem.entity.Appointment;

public interface AppointmentService {
    ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest);
    ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest);
    ResultData<AppointmentResponse> findById(Long id);
    Result delete(Long id);
}
