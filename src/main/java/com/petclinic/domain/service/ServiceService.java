package com.petclinic.domain.service;

import com.petclinic.domain.dto.ServiceDTO;
import com.petclinic.domain.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public Iterable<ServiceDTO> findAll() {
        return serviceRepository.findAll();
    }

    public ServiceDTO findById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));
    }

    public ServiceDTO save(ServiceDTO serviceDTO) {
        return serviceRepository.save(serviceDTO);
    }

    public ServiceDTO update(ServiceDTO serviceDTO) {
        return serviceRepository.update(serviceDTO);
    }

    public void delete(Long id) {
        serviceRepository.delete(id);
    }

    public boolean existsById(Long id) {
        return serviceRepository.existsById(id);
    }

    public long count() {
        return serviceRepository.count();
    }
}
