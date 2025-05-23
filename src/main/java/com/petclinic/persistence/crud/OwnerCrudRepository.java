package com.petclinic.persistence.crud;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.petclinic.persistence.entity.Owner;


public interface OwnerCrudRepository extends JpaRepository<Owner, Long> {
    
    @Query(value = "SELECT * FROM owners WHERE id = :id AND password = :password", nativeQuery = true)
    Optional<Owner> login(@Param("id") Long id, @Param("password") String password);
}
