package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.customer.CustomerRequestDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerRequestNitDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerResponseNameAddrbyNItDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;
import com.ayd2.intelafbackend.dto.customer.update.CustomerUpdateResponseDTO;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDTO> findAll();

    CustomerResponseNameAddrbyNItDTO findByNIt(CustomerRequestNitDTO customerRequestNitDTO) throws EntityNotFoundException;

    CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) throws NotAcceptableException;

    CustomerResponseDTO editCustomer(CustomerRequestDTO customerRequestDTO) throws EntityNotFoundException, NotAcceptableException;

    CustomerUpdateResponseDTO findUpdate(Long userIdUser) throws EntityNotFoundException;
}
