package com.petclinic.persistence.crud;



import org.springframework.data.jpa.repository.JpaRepository;

import com.petclinic.persistence.entity.Service;

public interface ServiceCrudRepository extends JpaRepository<Service, Long> {
}