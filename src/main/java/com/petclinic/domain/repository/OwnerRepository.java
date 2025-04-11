package com.petclinic.domain.repository;

import com.petclinic.domain.dto.OwnerDTO;
import java.util.Optional;

public interface OwnerRepository {

    Iterable<OwnerDTO> findAll();

    Optional<OwnerDTO> findById(Long id);

    OwnerDTO save(OwnerDTO ownerDTO);

    OwnerDTO update(OwnerDTO ownerDTO);

    void delete(Long id);

    boolean existsById(Long id);

    long count();

    
}