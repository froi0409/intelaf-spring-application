package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.customer.CustomerRequestDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerRequestNitDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerResponseNameAddrbyNItDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;
import com.ayd2.intelafbackend.dto.customer.update.CustomerUpdateResponseDTO;
import com.ayd2.intelafbackend.dto.user.UserRequestDTO;
import com.ayd2.intelafbackend.dto.user.UserResponseDTO;
import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.entities.users.User;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.exceptions.NotFoundException;
import com.ayd2.intelafbackend.repositories.CustomerRepository;
import com.ayd2.intelafbackend.repositories.UserRepository;
import com.ayd2.intelafbackend.services.CustomerService;
import com.ayd2.intelafbackend.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, UserService userService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
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
    @Transactional
    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO customerRequestDTO) throws NotAcceptableException {
        UserRequestDTO userRequestDTO =  new UserRequestDTO(customerRequestDTO);
        User userResponseDTO = userService.createUser(userRequestDTO);
        Customer customer = new Customer();
        customer.setCredit(customerRequestDTO.getCredit());
        customer.setUser(userResponseDTO);
        customer = customerRepository.save(customer);
        return new CustomerResponseDTO(customer);
    }

    @Override
    @Transactional
    public CustomerResponseDTO editCustomer(CustomerRequestDTO customerRequestDTO) throws NotFoundException, NotAcceptableException {
        UserRequestDTO userRequestDTO =  new UserRequestDTO(customerRequestDTO);
        User userResponse = userService.editUser(userRequestDTO);
        Customer existingCustomer = customerRepository.findById(userResponse.getIdUser())
                .orElseThrow(() -> new NotFoundException("customer not found"));
        existingCustomer.setCredit(customerRequestDTO.getCredit());
        existingCustomer = customerRepository.save(existingCustomer);
        return new CustomerResponseDTO(existingCustomer);
    }

    @Override
    public CustomerUpdateResponseDTO findUpdate(Long userIdUser) throws NotFoundException {
        Customer existingCustomer = customerRepository.findById(userIdUser)
                .orElseThrow(() -> new NotFoundException("customer not found"));
        return new CustomerUpdateResponseDTO(existingCustomer);
    }
}
