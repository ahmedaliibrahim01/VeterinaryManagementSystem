package service.concretes;

import com.ahmed.veterinaryManagementSystem.repository.AvailableDateRepository;
import service.abstracts.AvailableDateService;

public class AvailableDateManager implements AvailableDateService {
    private final AvailableDateRepository availableDateRepository;

    public AvailableDateManager(AvailableDateRepository availableDateRepository) {
        this.availableDateRepository = availableDateRepository;
    }
}
