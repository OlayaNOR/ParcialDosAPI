package com.petclinic.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "owners")
public class Owner {
    @Id
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(length = 100)
    private String email;
    
    @Column(length = 100)
    private String phone;

    @Column(length = 100)
    private String password;
    
}