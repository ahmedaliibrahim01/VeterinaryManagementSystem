package service.concretes;

import com.ahmed.veterinaryManagementSystem.repository.DoctorRepository;
import service.abstracts.DoctorService;

public class DoctorManager implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorManager(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
}
