package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.customer.CustomerRequestDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerRequestNitDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerResponseNameAddrbyNItDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.exceptions.NotFoundException;
import com.ayd2.intelafbackend.repositories.CustomerRepository;
import com.ayd2.intelafbackend.repositories.UserRepository;
import com.ayd2.intelafbackend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private UserRepository userRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerResponseDTO :: new)
                .collect(Collectors.toList());

    }

    @Override
    public CustomerResponseNameAddrbyNItDTO findByNIt(CustomerRequestNitDTO customerRequestNitDTO) {
        Customer customer = customerRepository.findByNit(customerRequestNitDTO.getNit())
                .orElseThrow(() -> new NotFoundException("customer not found"));
        return new CustomerResponseNameAddrbyNItDTO(customer);
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) {

        return null;
    }
}
