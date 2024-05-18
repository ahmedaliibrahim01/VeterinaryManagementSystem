package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.mapper.AvailableDateMapper;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AvailableDateUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AvailableDateResponse;
import com.ahmed.veterinaryManagementSystem.dto.response.DoctorResponse;
import com.ahmed.veterinaryManagementSystem.entity.AvailableDate;
import com.ahmed.veterinaryManagementSystem.entity.Doctor;
import com.ahmed.veterinaryManagementSystem.repository.AvailableDateRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AvailableDateService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvailableDateManager implements AvailableDateService {
    private final AvailableDateMapper mapper;
    private final AvailableDateRepository availableDateRepository;
    private final DoctorManager doctorManager;

    public AvailableDateManager(AvailableDateMapper mapper, AvailableDateRepository availableDateRepository, DoctorManager doctorManager) {
        this.mapper = mapper;
        this.availableDateRepository = availableDateRepository;
        this.doctorManager = doctorManager;
    }

    @Override
    public ResultData<AvailableDateResponse> save(AvailableDateSaveRequest availableDateSaveRequest) {
        // Check if the doctor exists
        DoctorResponse doctorResponse = this.doctorManager.findById(availableDateSaveRequest.getDoctorId()).getData();
        if (doctorResponse == null) {
            throw new EntityNotFoundException("Doctor with ID " + availableDateSaveRequest.getDoctorId() + " not found.");
        }

        // Check if an available date with the same properties already exists
        Optional<AvailableDate> existingAvailableDateOptional = availableDateRepository.findByDoctorIdAndAvailable(
                availableDateSaveRequest.getDoctorId(), availableDateSaveRequest.getAvailableDate());
        if (existingAvailableDateOptional.isPresent()) {
            // If an available date with the same properties already exists, throw an exception
            throw new IllegalStateException("Available date already exists for doctor with ID "
                    + availableDateSaveRequest.getDoctorId()
                    + " and date " + availableDateSaveRequest.getAvailableDate());
        }

        // Save the available date
        AvailableDate saveAvailableDate = this.mapper.saveAvailableDate(availableDateSaveRequest);
        this.availableDateRepository.save(saveAvailableDate);
        return ResultInfo.created(this.mapper.toAvailableDateResponse(saveAvailableDate));
    }


    @Override
    public ResultData<AvailableDateResponse> update(AvailableDateUpdateRequest availableDateUpdateRequest) {
        Long availableDateId = availableDateUpdateRequest.getId();
        AvailableDate existingAvailableDate = this.availableDateRepository.findById(availableDateId)
                .orElseThrow(() -> new EntityNotFoundException("AvailableDate with ID " + availableDateId + " not found."));

        Long doctorId = availableDateUpdateRequest.getDoctorId();
        Doctor existingDoctor = this.doctorManager.findDoctorId(doctorId);

        AvailableDate updatedAvailableDate = this.mapper.updateAvailableDate(availableDateUpdateRequest);
        updatedAvailableDate.setId(availableDateId);
        this.availableDateRepository.save(updatedAvailableDate);

        return ResultInfo.success(this.mapper.toAvailableDateResponse(updatedAvailableDate));
    }

    @Override
    public ResultData<AvailableDateResponse> findById(Long id) {
        AvailableDate availableDate = this.availableDateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AvailableDate with ID " + id + " not found."));
        return ResultInfo.success(this.mapper.toAvailableDateResponse(availableDate));
    }

    @Override
    public Result delete(Long id) {
        AvailableDate availableDate = this.availableDateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AvailableDate with ID " + id + " not found."));
        this.availableDateRepository.delete(availableDate);
        return ResultInfo.ok();
    }
}
