package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;
import com.ayd2.intelafbackend.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
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
}
