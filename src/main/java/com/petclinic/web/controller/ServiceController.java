package com.petclinic.web.controller;

import com.petclinic.domain.dto.ServiceDTO;
import com.petclinic.domain.service.ServiceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController{

    @Autowired
    private ServiceService serviceService;

    @Operation(summary = "Get all services", description = "Returns a list of all registered services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/all")
    public ResponseEntity<Iterable<ServiceDTO>> getAllServices() {
        Iterable<ServiceDTO> services = serviceService.findAll();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @Operation(summary = "Get service by ID", description = "Returns the service associated with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service found"),
            @ApiResponse(responseCode = "404", description = "Service not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable("id") Long id) {
        Optional<ServiceDTO> service = Optional.ofNullable(serviceService.findById(id));
        return service.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Save a new service", description = "Saves a new service in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Service created successfully"),
            @ApiResponse(responseCode = "405", description = "Method not allowed"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping("/save")
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO savedService = serviceService.save(serviceDTO);
        return new ResponseEntity<>(savedService, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a service", description = "Updates the data of an existing service")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service updated successfully"),
            @ApiResponse(responseCode = "404", description = "Service not found"),
            @ApiResponse(responseCode = "405", description = "Method not allowed")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<ServiceDTO> updateService(@PathVariable("id") Long id, @RequestBody ServiceDTO serviceDTO) {
        serviceDTO.setId(id);
        return ResponseEntity.ok(serviceService.update(serviceDTO));
    }

    @Operation(summary = "Delete a service", description = "Deletes the service associated with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Service deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Service not found"),
            @ApiResponse(responseCode = "405", description = "Method not allowed")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable("id") Long id) {
        serviceService.delete(id);
        return ResponseEntity.ok().build();
    }

}

