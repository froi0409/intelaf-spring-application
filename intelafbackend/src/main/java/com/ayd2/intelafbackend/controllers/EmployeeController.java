/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.employee.EmployeeRequestDTO;
import com.ayd2.intelafbackend.dto.employee.EmployeeResponseDTO;
import com.ayd2.intelafbackend.dto.products.ProductResponseDTO;
import com.ayd2.intelafbackend.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author waliray
 */
@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {
    private EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    } 
    
    @GetMapping("/employee-by-username/{username}")
    public ResponseEntity<EmployeeResponseDTO> findEmployeeById(@PathVariable("username") String username){
        return  ResponseEntity.status(HttpStatus.OK).body(employeeService.findByUsername(username));
    }
    
    @GetMapping("/list-all-employees")
    public ResponseEntity<List<EmployeeResponseDTO>> listAllEmployees() {        
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.findAll());
    }
    
    @PostMapping("/create-employee")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(@RequestBody EmployeeRequestDTO newEmployee) {
        EmployeeResponseDTO responseDTO = employeeService.createEmployee(newEmployee);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }
    
    @PutMapping("/update-employee/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeRequestDTO updatedEmployee) {
        EmployeeResponseDTO responseDTO = employeeService.updateEmployee(id, updatedEmployee);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
