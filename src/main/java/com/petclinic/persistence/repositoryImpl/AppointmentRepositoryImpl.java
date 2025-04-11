package com.petclinic.persistence.repositoryImpl;

import com.petclinic.domain.dto.AppointmentDTO;
import com.petclinic.domain.repository.AppointmentRepository;
import com.petclinic.persistence.crud.AppointmentCrudRepository;
import com.petclinic.persistence.entity.Appointment;
import com.petclinic.persistence.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AppointmentRepositoryImpl implements AppointmentRepository {

    @Autowired
    private AppointmentCrudRepository appointmentCrudRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public Iterable<AppointmentDTO> findAll() {
        Iterable<Appointment> appointments = appointmentCrudRepository.findAll();
        return ((List<Appointment>) appointments).stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppointmentDTO> findById(Long id) {
        Optional<Appointment> appointment = appointmentCrudRepository.findById(id);
        return appointment.map(appointmentMapper::toDto);
    }

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
        if (!existsById(appointment.getId())) {
            Appointment savedAppointment = appointmentCrudRepository.save(appointment);
            return appointmentMapper.toDto(savedAppointment);
        }
        throw new IllegalArgumentException("The record already exists");
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        Appointment appointment = appointmentMapper.toEntity(appointmentDTO);
        if (existsById(appointment.getId())) {
            Appointment updatedAppointment = appointmentCrudRepository.save(appointment);
            return appointmentMapper.toDto(updatedAppointment);
        }
        throw new IllegalArgumentException("The record does not exist");
    }

    @Override
    public void delete(Long id) {
        if (existsById(id)) {
            appointmentCrudRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("The record does not exist");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return appointmentCrudRepository.existsById(id);
    }

    @Override
    public long count() {
        return appointmentCrudRepository.count();
    }
}