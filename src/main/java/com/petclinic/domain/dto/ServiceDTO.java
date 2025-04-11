package com.petclinic.domain.dto;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ServiceDTO {
    private Long id;
    private String name;
    private Double cost;

    public ServiceDTO(Long id, String name, Double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
}