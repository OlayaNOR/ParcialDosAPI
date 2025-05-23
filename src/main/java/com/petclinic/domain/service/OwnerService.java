package com.petclinic.domain.service;

import com.petclinic.domain.dto.OwnerDTO;
import com.petclinic.domain.repository.OwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Iterable<OwnerDTO> findAll() {
        return ownerRepository.findAll();
    }

    public OwnerDTO findById(Long id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));
    }


    public OwnerDTO login(Long id, String password) {
        return ownerRepository.login(id, password)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public OwnerDTO save(OwnerDTO ownerDTO) {
        return ownerRepository.save(ownerDTO);
    }

    public OwnerDTO update(OwnerDTO ownerDTO) {
        return ownerRepository.update(ownerDTO);
    }

    public void delete(Long id) {
        ownerRepository.delete(id);
    }

    public boolean existsById(Long id) {
        return ownerRepository.existsById(id);
    }

    public long count() {
        return ownerRepository.count();
    }

}