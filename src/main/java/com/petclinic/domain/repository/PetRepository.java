package com.petclinic.domain.repository;

import com.petclinic.domain.dto.PetDTO;


import java.util.List;
import java.util.Optional;

public interface PetRepository {

    Iterable<PetDTO> findAll();

    Optional<PetDTO> findById(Long id);

    PetDTO save(PetDTO petDTO);

    PetDTO update(PetDTO petDTO);

    void delete(Long id);

    boolean existsById(Long id);

    long count();

    List<PetDTO> findPetsByOwnerId(Long owner_id);

    List<PetDTO> findByAgeLessThan(int age);
}