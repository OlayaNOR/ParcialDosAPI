package com.petclinic.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petclinic.persistence.entity.Owner;


public interface OwnerCrudRepository extends JpaRepository<Owner, Long> {
    
}
