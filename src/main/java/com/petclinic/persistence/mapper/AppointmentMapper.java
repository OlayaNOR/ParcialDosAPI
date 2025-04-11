package com.petclinic.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;

import com.petclinic.domain.dto.AppointmentDTO;
import com.petclinic.persistence.entity.Appointment;

@Mapper(componentModel = "spring", uses = {PetMapper.class, ServiceMapper.class})
public interface AppointmentMapper {
    
    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    // Mapping Appointment → AppointmentDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "pet", target = "pet")
    @Mapping(source = "service", target = "service")
    @Mapping(source = "date", target = "date")
    AppointmentDTO toDto(Appointment appointment);

    // Reverse mapping AppointmentDTO → Appointment
    @InheritInverseConfiguration
    Appointment toEntity(AppointmentDTO appointmentDTO);
}