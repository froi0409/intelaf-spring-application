package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;
import com.ayd2.intelafbackend.dto.employee.EmployeeRequestDTO;
import com.ayd2.intelafbackend.dto.employee.EmployeeResponseDTO;
import com.ayd2.intelafbackend.dto.user.UserResponseDTO;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> findAll();
    
    EmployeeResponseDTO createEmployee(EmployeeRequestDTO newEmployee);
    
    EmployeeResponseDTO updateEmployee(String id, EmployeeRequestDTO updatedEmployee);
}
