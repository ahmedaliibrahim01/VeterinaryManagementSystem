package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.repository.VaccineRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.VaccineService;
import org.springframework.stereotype.Service;

@Service
public class VaccineManager implements VaccineService {
    private final VaccineRepository vaccineRepository;

    public VaccineManager(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }
}