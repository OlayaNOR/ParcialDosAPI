package com.petclinic.persistence.crud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petclinic.persistence.entity.Pet;

public interface PetCrudRepository extends JpaRepository<Pet, Long> {
    //Como owner, quiero obtener la lista de todas mis mascotas
    List<Pet> findPetsByOwnerId(Long ownerId);

    //Como admin, quiero obtener la lista de todas las mascotas que tengan menos de 2 years
    List<Pet> findByAgeLessThan(int age);
}
