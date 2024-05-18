package com.ahmed.veterinaryManagementSystem.service.concretes;

import com.ahmed.veterinaryManagementSystem.core.result.Result;
import com.ahmed.veterinaryManagementSystem.core.result.ResultData;
import com.ahmed.veterinaryManagementSystem.core.utils.ResultInfo;
import com.ahmed.veterinaryManagementSystem.dto.mapper.AppointmentMapper;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentSaveRequest;
import com.ahmed.veterinaryManagementSystem.dto.request.AppointmentUpdateRequest;
import com.ahmed.veterinaryManagementSystem.dto.response.AnimalResponse;
import com.ahmed.veterinaryManagementSystem.dto.response.AppointmentResponse;
import com.ahmed.veterinaryManagementSystem.entity.Animal;
import com.ahmed.veterinaryManagementSystem.entity.Appointment;
import com.ahmed.veterinaryManagementSystem.repository.AnimalRepository;
import com.ahmed.veterinaryManagementSystem.repository.AppointmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.ahmed.veterinaryManagementSystem.service.abstracts.AppointmentService;

@Service
public class AppointmentManager implements AppointmentService {
    private final AppointmentMapper mapper;
    private final AppointmentRepository appointmentRepository;
    private final AnimalManager animalManager;

    public AppointmentManager(AppointmentMapper mapper, AppointmentRepository appointmentRepository, AnimalManager animalManager) {
        this.mapper = mapper;
        this.appointmentRepository = appointmentRepository;
        this.animalManager = animalManager;
    }

    @Override
    public ResultData<AppointmentResponse> save(AppointmentSaveRequest appointmentSaveRequest) {
        AnimalResponse animalResponse = this.animalManager
                .findById(appointmentSaveRequest.getAnimalId()).getData();
        if (animalResponse == null){
            throw new EntityNotFoundException("Animal not found.");
        }
        Appointment saveAppointment = this.mapper.saveAppointment(appointmentSaveRequest);
        this.appointmentRepository.save(saveAppointment);
        return ResultInfo.created(this.mapper.toAppointmentResponse(saveAppointment));
    }
    @Override
    public ResultData<AppointmentResponse> update(AppointmentUpdateRequest appointmentUpdateRequest) {
        Long appointmentId = appointmentUpdateRequest.getId();
        Appointment existingAppointment = this.appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + appointmentId + " not found."));

        Appointment updatedAppointment = this.mapper.updateAppointment(appointmentUpdateRequest);
        updatedAppointment.setId(appointmentId);
        this.appointmentRepository.save(updatedAppointment);

        return ResultInfo.success(this.mapper.toAppointmentResponse(updatedAppointment));
    }

    @Override
    public ResultData<AppointmentResponse> findById(Long id) {
        Appointment appointment = this.appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " not found."));
        return ResultInfo.success(this.mapper.toAppointmentResponse(appointment));
    }

    @Override
    public Result delete(Long id) {
        Appointment appointment = this.appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " not found."));
        this.appointmentRepository.delete(appointment);
        return ResultInfo.ok();
    }

    public Appointment findAppointmentId(Long appointmentId) {
        return this.appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + appointmentId + " not found."));
    }
}
