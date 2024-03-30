/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.employee.EmployeeRequestDTO;
import com.ayd2.intelafbackend.dto.employee.EmployeeResponseDTO;
import java.util.List;

/**
 *
 * @author waliray
 */
public interface EmployeeService {
    List<EmployeeResponseDTO> findAll();
    EmployeeResponseDTO findByUsername(String username);
    
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO newEmployee);

    EmployeeResponseDTO updateEmployee(String id, EmployeeRequestDTO updatedEmployee);
}
