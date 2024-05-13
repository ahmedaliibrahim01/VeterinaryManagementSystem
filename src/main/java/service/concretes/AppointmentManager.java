package service.concretes;

import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import service.abstracts.AnimalService;
import service.abstracts.AppointmentService;

@Service
public class AppointmentManager implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentManager(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
}
