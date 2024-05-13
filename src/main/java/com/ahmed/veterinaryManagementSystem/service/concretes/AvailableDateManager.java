package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.repository.AvailableDateRepository;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AvailableDateService;
import org.springframework.stereotype.Service;

@Service
public class AvailableDateManager implements AvailableDateService {
    private final AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }
}
