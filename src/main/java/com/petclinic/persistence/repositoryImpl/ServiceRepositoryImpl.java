package com.petclinic.persistence.repositoryImpl;

import com.petclinic.domain.dto.ServiceDTO;
import com.petclinic.domain.repository.ServiceRepository;
import com.petclinic.persistence.crud.ServiceCrudRepository;
import com.petclinic.persistence.entity.Service;
import com.petclinic.persistence.mapper.ServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ServiceRepositoryImpl implements ServiceRepository {

    @Autowired
    private ServiceCrudRepository serviceCrudRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public List<ServiceDTO> findAll() {
        List<Service> services = serviceCrudRepository.findAll();
        return ((List<Service>) services).stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServiceDTO> findById(Long id) {
        Optional<Service> service = serviceCrudRepository.findById(id);
        return service.map(serviceMapper::toDto);
    }

    @Override
    public ServiceDTO save(ServiceDTO serviceDTO) {
        Service service = serviceMapper.toEntity(serviceDTO);
        if (!existsById(service.getId())) {
            Service savedService = serviceCrudRepository.save(service);
            return serviceMapper.toDto(savedService);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The record does not exist");
    }

    @Override
    public ServiceDTO update(ServiceDTO serviceDTO) {
        Service service = serviceMapper.toEntity(serviceDTO);
        if (existsById(service.getId())) {
            Service updatedService = serviceCrudRepository.save(service);
            return serviceMapper.toDto(updatedService);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The record does not exist");
    }

    @Override
    public void delete(Long id) {
        if (existsById(id)) {
            serviceCrudRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The record does not exist");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return serviceCrudRepository.existsById(id);
    }

    @Override
    public long count() {
        return serviceCrudRepository.count();
    }
}