package com.petclinic.domain.repository;

import com.petclinic.domain.dto.ServiceDTO;

import java.util.Optional;

public interface ServiceRepository {

    Iterable<ServiceDTO> findAll();

    Optional<ServiceDTO> findById(Long id);

    ServiceDTO save(ServiceDTO serviceDTO);

    ServiceDTO update(ServiceDTO serviceDTO);

    void delete(Long id);

    boolean existsById(Long id);

    long count();
}