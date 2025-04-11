package com.petclinic.persistence.repositoryImpl;

import com.petclinic.domain.dto.PetDTO;
import com.petclinic.domain.repository.PetRepository;
import com.petclinic.persistence.crud.PetCrudRepository;
import com.petclinic.persistence.entity.Pet;
import com.petclinic.persistence.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PetRepositoryImpl implements PetRepository {

    @Autowired
    private PetCrudRepository petCrudRepository;

    @Autowired
    private PetMapper petMapper;

    @Override
    public Iterable<PetDTO> findAll() {
        Iterable<Pet> pets = petCrudRepository.findAll();
        return ((List<Pet>) pets).stream()
                .map(petMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PetDTO> findById(Long id) {
        Optional<Pet> pet = petCrudRepository.findById(id);
        return pet.map(petMapper::toDto);
    }

    @Override
    public PetDTO save(PetDTO petDTO) {
        Pet pet = petMapper.toEntity(petDTO);
        if (!existsById(pet.getId())) {
            Pet savedPet = petCrudRepository.save(pet);
            return petMapper.toDto(savedPet);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The record does not exist");
    }

    @Override
    public PetDTO update(PetDTO petDTO) {
        Pet pet = petMapper.toEntity(petDTO);
        if (existsById(pet.getId())) {
            Pet updatedPet = petCrudRepository.save(pet);
            return petMapper.toDto(updatedPet);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The record does not exist");
    }

    @Override
    public void delete(Long id) {
        if (existsById(id)) {
            petCrudRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The record does not exist");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return petCrudRepository.existsById(id);
    }

    @Override
    public long count() {
        return petCrudRepository.count();
    }

    @Override
    public List<PetDTO> findPetsByOwnerId(Long id_owner) {
        List<Pet> pets = petCrudRepository.findPetsByOwnerId(id_owner);
            return pets.stream()
                    .map(petMapper::toDto)
                    .collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> findByAgeLessThan(int age) {
        List<Pet> pets = petCrudRepository.findByAgeLessThan(age);
            return pets.stream()
                    .map(petMapper::toDto)
                    .collect(Collectors.toList());
    }

    
}