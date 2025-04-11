package com.petclinic.persistence.repositoryImpl;

import com.petclinic.domain.dto.OwnerDTO;
import com.petclinic.domain.repository.OwnerRepository;
import com.petclinic.persistence.crud.OwnerCrudRepository;
import com.petclinic.persistence.entity.Owner;
import com.petclinic.persistence.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository{

    @Autowired
    private OwnerCrudRepository ownerCrudRepository;

    @Autowired
    private OwnerMapper ownerMapper;

    // Retrieve all records
    @Override
    public Iterable<OwnerDTO> findAll() {
        Iterable<Owner> owners = ownerCrudRepository.findAll();
        return ((List<Owner>) owners).stream()
                .map(ownerMapper::toDto)
                .collect(Collectors.toList());
    }

    // Retrieve by ID
    @Override
    public Optional<OwnerDTO> findById(Long id) {
        Optional<Owner> owner = ownerCrudRepository.findById(id);
        return owner.map(ownerMapper::toDto);
    }

    // Save a new record
    @Override
    public OwnerDTO save(OwnerDTO ownerDTO) {
        Owner owner = ownerMapper.toEntity(ownerDTO);
        if (!existsById(owner.getId())) {
            Owner savedOwner = ownerCrudRepository.save(owner);
            return ownerMapper.toDto(savedOwner);
        }
        throw new IllegalArgumentException("The record already exists");
    }

    // Update an existing record
    @Override
    public OwnerDTO update(OwnerDTO ownerDTO) {
        Owner owner = ownerMapper.toEntity(ownerDTO);
        if (existsById(owner.getId())) {
            Owner updatedOwner = ownerCrudRepository.save(owner);
            return ownerMapper.toDto(updatedOwner);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The record does not exist");
    }

    // Delete a record
    @Override
    public void delete(Long id) {
        if (existsById(id)) {
            ownerCrudRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The record does not exist");
        }
    }

    // Check if a record exists by ID
    @Override
    public boolean existsById(Long id) {
        return ownerCrudRepository.existsById(id);
    }

    // Count all records
    @Override
    public long count() {
        return ownerCrudRepository.count();
    }

    
    
    
}