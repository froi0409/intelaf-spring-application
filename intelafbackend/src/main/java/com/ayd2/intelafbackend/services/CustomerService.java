package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerResponseDTO> findAll();
}
