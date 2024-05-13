package service.concretes;

import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import service.abstracts.AnimalService;
@Service
public class AnimalManager implements AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalManager(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

}
