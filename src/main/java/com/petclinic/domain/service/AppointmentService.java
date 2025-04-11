package com.petclinic.domain.service;

import com.petclinic.domain.dto.AppointmentDTO;
import com.petclinic.domain.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Iterable<AppointmentDTO> findAll() {
        return appointmentRepository.findAll();
    }

    public AppointmentDTO findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    }

    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        return appointmentRepository.save(appointmentDTO);
    }

    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        return appointmentRepository.update(appointmentDTO);
    }

    public void delete(Long id) {
        appointmentRepository.delete(id);
    }

    public boolean existsById(Long id) {
        return appointmentRepository.existsById(id);
    }

    public long count() {
        return appointmentRepository.count();
    }
}