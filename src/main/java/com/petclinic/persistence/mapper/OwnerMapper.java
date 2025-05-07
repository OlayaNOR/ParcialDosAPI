package com.petclinic.persistence.mapper;

import com.petclinic.domain.dto.OwnerDTO;
import com.petclinic.persistence.entity.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;

@Mapper(componentModel = "spring")
public interface OwnerMapper {

    // Mapping Owner → OwnerDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "password", target = "password")
    OwnerDTO toDto(Owner owner);

    // Reverse mapping OwnerDTO → Owner
    @InheritInverseConfiguration
    Owner toEntity(OwnerDTO ownerDTO);
}