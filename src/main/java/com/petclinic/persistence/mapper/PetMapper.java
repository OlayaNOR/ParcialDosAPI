package com.petclinic.persistence.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;
import com.petclinic.domain.dto.PetDTO;
import com.petclinic.persistence.entity.Pet;

@Mapper(componentModel = "spring", uses = {OwnerMapper.class})
public interface PetMapper {
    
    PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);

    // Mapping Pet → PetDTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "gender", target = "gender")
    @Mapping(source = "age", target = "age")
    @Mapping(source = "owner", target = "owner")
    PetDTO toDto(Pet pet);

    // Reverse mapping PetDTO → Pet
    @InheritInverseConfiguration 
    Pet toEntity(PetDTO pet);
}