package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.customer.CustomerRequestDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerRequestNitDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;
import com.ayd2.intelafbackend.dto.customer.CustomerResponseNameAddrbyNItDTO;
import com.ayd2.intelafbackend.dto.customer.update.CustomerUpdateResponseDTO;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('EMPLOYEE')")
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("all")
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping("findById/{nit}")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<CustomerResponseNameAddrbyNItDTO> findByNIt(@PathVariable String nit) throws EntityNotFoundException {
        CustomerResponseNameAddrbyNItDTO customerResponseNameAddrbyNItDTO = customerService.findByNIt(new CustomerRequestNitDTO(nit));
        return ResponseEntity.status(HttpStatus.OK).body(customerResponseNameAddrbyNItDTO);
    }

    @PostMapping("create")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) throws NotAcceptableException {
        CustomerResponseDTO customerResponseDTO = customerService.createCustomer(customerRequestDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(customerResponseDTO);
    }

    @PutMapping("update")
    public ResponseEntity<CustomerResponseDTO> editCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) throws NotAcceptableException {
        CustomerResponseDTO customerResponseDTO = customerService.editCustomer(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponseDTO);
    }

    @GetMapping("findUpdate/{userIdUser}")
    public ResponseEntity<CustomerUpdateResponseDTO> findUpdate(@PathVariable Long userIdUser) throws EntityNotFoundException {
        CustomerUpdateResponseDTO customerUpdateResponseDTO = customerService.findUpdate(userIdUser);
        return ResponseEntity.status(HttpStatus.OK).body(customerUpdateResponseDTO);
    }
}
