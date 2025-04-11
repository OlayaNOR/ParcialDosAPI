package com.petclinic.web.controller;

import com.petclinic.domain.dto.PetDTO;
import com.petclinic.domain.service.PetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {
    
    @Autowired
    private PetService petService;

    @Operation(summary = "Get all pets", description = "Returns a list of all registered pets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<PetDTO>> getAllPets() {
        Iterable<PetDTO> pets = petService.findAll();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @Operation(summary = "Get pet by ID", description = "Returns the pet associated with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet found"),
            @ApiResponse(responseCode = "404", description = "Pet not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable("id") Long id) {
        Optional<PetDTO> pet = Optional.ofNullable(petService.findById(id));
        return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Save a new pet", description = "Saves a new pet in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pet created successfully"),
            @ApiResponse(responseCode = "405", description = "Method not allowed"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/save")
    public ResponseEntity<PetDTO> createPet(@RequestBody PetDTO petDTO) {
        PetDTO savedPet = petService.save(petDTO);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a pet", description = "Updates the data of an existing pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet updated successfully"),
            @ApiResponse(responseCode = "400", description = "Error updating the owner"),
            @ApiResponse(responseCode = "404", description = "Pet not found"),
            @ApiResponse(responseCode = "405", description = "Method not allowed")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable("id") Long id, @RequestBody PetDTO petDTO) {
        petDTO.setId(id);
        return ResponseEntity.ok(petService.update(petDTO));
    }

    @Operation(summary = "Delete a pet", description = "Deletes the pet associated with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pet deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Pet not found"),
            @ApiResponse(responseCode = "405", description = "Method not allowed")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable("id") Long id) {
        petService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get all pets by owner Id", description = "Returns a list of all registered pets by a specific owner Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Owner Not found")
    })
    @GetMapping("/all/{id_owner}")
    public ResponseEntity<Iterable<PetDTO>> getAllPetsByOwnerId(@PathVariable("id_owner") Long id_owner) {
        Iterable<PetDTO> pets = petService.findPetsByOwnerId(id_owner);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @Operation(summary = "Get all pets with age lower than 2 years", description = "Returns a list of all registered pets with 1 or 0 years")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "409", description = "Invalid Data")
    })
    @GetMapping("/all/filter")
    public ResponseEntity<Iterable<PetDTO>> getAllPetsByAgeLessThan(@RequestParam("age") int age) {
        Iterable<PetDTO> pets = petService.findPetsByAgeLessThan(age);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }
}


