package com.petclinic.domain.service;

import com.petclinic.domain.dto.PetDTO;
import com.petclinic.domain.repository.PetRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PetService {

    private final OwnerService ownerService;

    @Autowired
    private PetRepository petRepository;

    PetService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public Iterable<PetDTO> findAll() {
        return petRepository.findAll();
    }

    public PetDTO findById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pet not found"));
    }

    public PetDTO save(PetDTO petDTO) {
        return petRepository.save(petDTO);
    }

    public PetDTO update(PetDTO petDTO) {
        return petRepository.update(petDTO);
    }

    public void delete(Long id) {
        petRepository.delete(id);
    }

    public boolean existsById(Long id) {
        return petRepository.existsById(id);
    }

    public long count() {
        return petRepository.count();
    }

    public List<PetDTO> findPetsByOwnerId(Long id_owner){
        if (!ownerService.existsById(id_owner)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The record does not exist");
        }else{
            
        }
        return petRepository.findPetsByOwnerId(id_owner);
    }

    public List<PetDTO> findPetsByAgeLessThan(int age){
        if (age<0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "invalid Data");
        }else{
            
        }
        return petRepository.findByAgeLessThan(age);
    }
}