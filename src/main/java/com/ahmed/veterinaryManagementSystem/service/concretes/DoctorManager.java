package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.repository.DoctorRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.DoctorService;
import org.springframework.stereotype.Service;

@Service
public class DoctorManager implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorManager(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
}
