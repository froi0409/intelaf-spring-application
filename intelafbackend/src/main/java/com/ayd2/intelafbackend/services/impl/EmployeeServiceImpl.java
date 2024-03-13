/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.employee.EmployeeResponseDTO;
import com.ayd2.intelafbackend.entities.users.Employee;
import com.ayd2.intelafbackend.repositories.EmployeeRepository;
import com.ayd2.intelafbackend.repositories.ProductRepository;
import com.ayd2.intelafbackend.services.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author waliray
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    @Override
    public List<EmployeeResponseDTO> findAll() {        
        Iterable<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponseDTO> employeeResponseDTOs = StreamSupport.stream(employees.spliterator(), false)
        .map(employee -> new EmployeeResponseDTO(employee.getUser(), employee))
        .collect(Collectors.toList());
        
        return employeeResponseDTOs;

    }
}
