package com.petclinic.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PetDTO {
    private Long id;
    private String name;
    private String type;
    private String gender;
    private int age;
    private OwnerDTO owner;

    public PetDTO(Long id, String name, String type, String gender, int age, OwnerDTO owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.gender = gender;
        this.age = age;
        this.owner = owner;
    }
}