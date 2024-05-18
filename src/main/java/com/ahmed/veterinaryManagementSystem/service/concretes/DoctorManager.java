package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.dto.mapper.DoctorMapper;
import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.DoctorUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.DoctorResponse;
import com.ahmed.veterinaryManagementSystem.entity.Doctor;
import com.ahmed.veterinaryManagementSystem.repository.DoctorRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements DoctorService {

    private final DoctorMapper mapper;
    private final DoctorRepository doctorRepository;

    public DoctorManager(DoctorMapper mapper, DoctorRepository doctorRepository) {
        this.mapper = mapper;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public ResultData<DoctorResponse> save(DoctorSaveRequest doctorSaveRequest) {
        Doctor saveDoctor = this.mapper.saveDoctor(doctorSaveRequest);
        if (doctorRepository.findByMail(saveDoctor.getMail()).isPresent()) {
            throw new IllegalArgumentException("Email address " + saveDoctor.getMail() + " is already registered.");
        }
        if (doctorRepository.findByPhone(saveDoctor.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Phone " + saveDoctor.getPhone() + " is already registered.");
        }
        this.doctorRepository.save(saveDoctor);
        return ResultInfo.created(this.mapper.toDoctorResponse(saveDoctor));
    }

    @Override
    public ResultData<DoctorResponse> update(DoctorUpdateRequest doctorUpdateRequest) {
        Long doctorId = doctorUpdateRequest.getId();
        Doctor existingDoctor = this.doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + doctorId + " not found."));

        Doctor updatedDoctor = this.mapper.updateDoctor(doctorUpdateRequest);
        updatedDoctor.setId(doctorId);
        this.doctorRepository.save(updatedDoctor);

        return ResultInfo.success(this.mapper.toDoctorResponse(updatedDoctor));
    }

    @Override
    public ResultData<DoctorResponse> findById(Long id) {
        Doctor doctor = this.doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + id + " not found."));
        return ResultInfo.success(this.mapper.toDoctorResponse(doctor));
    }

    @Override
    public Result delete(Long id) {
        Doctor doctor = this.doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + id + " not found."));
        this.doctorRepository.delete(doctor);
        return ResultInfo.ok();
    }

    public Doctor findDoctorId(Long doctorId) {
        return this.doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + doctorId + " not found."));
    }
}
