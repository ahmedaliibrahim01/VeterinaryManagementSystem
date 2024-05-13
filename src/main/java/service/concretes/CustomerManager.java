package service.concretes;

import com.ahmed.veterinaryManagementSystem.repository.CustomerRepository;
import service.abstracts.CustomerService;

public class CustomerManager implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerManager(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
