package com.petclinic.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private PetDTO pet;
    private ServiceDTO service;
    private LocalDate date;

    public AppointmentDTO(Long id, PetDTO pet, ServiceDTO service, LocalDate date) {
        this.id = id;
        this.pet= pet;
        this.service = service;
        this.date = date;
    }
}