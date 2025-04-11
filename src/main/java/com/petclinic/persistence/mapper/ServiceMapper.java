package com.petclinic.persistence.mapper;


import org.mapstruct.Mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.petclinic.domain.dto.ServiceDTO;
import com.petclinic.persistence.entity.Service;


@Mapper(componentModel = "spring")
public interface ServiceMapper {
    
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    // Mapping Service → ServiceDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "cost", target = "cost")
    ServiceDTO toDto(Service service);

    // Reverse mapping ServiceDTO → Service
    @InheritInverseConfiguration 
    Service toEntity(ServiceDTO serviceDTO);
}