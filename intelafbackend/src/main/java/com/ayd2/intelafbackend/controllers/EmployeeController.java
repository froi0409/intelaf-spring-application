/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.products.ProductResponseDTO;
import com.ayd2.intelafbackend.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
//    @GetMapping("/get-role/{id_user}")
//    public ResponseEntity<List<ProductResponseDTO>> getRoleById(@PathVariable Long id_user) {
//        return ResponseEntity.ok(employeeService.findAll());
//    }
    
}
