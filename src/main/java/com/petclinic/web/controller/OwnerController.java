package com.petclinic.web.controller;


import com.petclinic.domain.dto.OwnerDTO;
import com.petclinic.domain.service.OwnerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @Operation(summary = "Get all owners", description = "Returns a list of all registered owners")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<OwnerDTO>> getAllOwners() {
        Iterable<OwnerDTO> customers = ownerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Operation(summary = "Get owner by ID", description = "Returns the owner associated with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner found"),
            @ApiResponse(responseCode = "404", description = "Owner not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable("id") Long id) {
        Optional<OwnerDTO> owner = Optional.ofNullable(ownerService.findById(id));
        return owner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Save a new owner", description = "Saves a new owner in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created successfully"),
            @ApiResponse(responseCode = "405", description = "Method not allowed"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/save")
    public ResponseEntity<OwnerDTO> createOwner(@RequestBody OwnerDTO customerDTO) {
        OwnerDTO savedOwner = ownerService.save(customerDTO);
        return new ResponseEntity<>(savedOwner, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a owner", description = "Updates the data of an existing owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner updated successfully"),
            @ApiResponse(responseCode = "400", description = "Error updating the owner"),
            @ApiResponse(responseCode = "404", description = "Owner not found"),
            @ApiResponse(responseCode = "405", description = "Method not allowed")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<OwnerDTO> updateOwner(@PathVariable("id") Long id, @RequestBody OwnerDTO ownerDTO) {
        ownerDTO.setId(id);
        return ResponseEntity.ok(ownerService.update(ownerDTO));
    }

    @Operation(summary = "Delete a owner", description = "Deletes the owner associated with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Owner deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Owner not found"),
            @ApiResponse(responseCode = "405", description = "Method not allowed")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable("id") Long id) {
        ownerService.delete(id);
        return ResponseEntity.ok().build();
    }

}
