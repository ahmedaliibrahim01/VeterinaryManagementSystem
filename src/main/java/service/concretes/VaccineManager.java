package service.concretes;

import com.ahmed.veterinaryManagementSystem.repository.VaccineRepository;
import service.abstracts.VaccineService;

public class VaccineManager implements VaccineService {
    private final VaccineRepository vaccineRepository;

    public VaccineManager(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }
}