package com.petclinic.domain.repository;

import com.petclinic.domain.dto.AppointmentDTO;

import java.util.Optional;

public interface AppointmentRepository {

    Iterable<AppointmentDTO> findAll();

    Optional<AppointmentDTO> findById(Long id);

    AppointmentDTO save(AppointmentDTO appointmentDTO);

    AppointmentDTO update(AppointmentDTO appointmentDTO);

    void delete(Long id);

    boolean existsById(Long id);

    long count();
}