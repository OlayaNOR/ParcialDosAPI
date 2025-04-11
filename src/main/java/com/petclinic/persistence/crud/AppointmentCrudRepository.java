package com.petclinic.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petclinic.persistence.entity.Appointment;

public interface AppointmentCrudRepository extends JpaRepository<Appointment, Long> {
}