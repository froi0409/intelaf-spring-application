package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;
import com.ayd2.intelafbackend.repositories.CustomerRepository;
import com.ayd2.intelafbackend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerResponseDTO :: new)
                .collect(Collectors.toList());

    }
}
