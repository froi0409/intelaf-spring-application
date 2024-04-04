/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.employee.EmployeeRequestDTO;
import com.ayd2.intelafbackend.dto.employee.EmployeeResponseDTO;
import com.ayd2.intelafbackend.entities.users.Employee;
import com.ayd2.intelafbackend.entities.users.User;
import com.ayd2.intelafbackend.enums.user.Role;
import com.ayd2.intelafbackend.repositories.EmployeeRepository;
import com.ayd2.intelafbackend.repositories.ProductRepository;
import com.ayd2.intelafbackend.repositories.UserRepository;
import com.ayd2.intelafbackend.services.EmployeeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author waliray
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public List<EmployeeResponseDTO> findAll() {        
        Iterable<Employee> employees = employeeRepository.findAll();

        List<EmployeeResponseDTO> employeeResponseDTOs = StreamSupport.stream(employees.spliterator(), false)
        .map(employee -> new EmployeeResponseDTO(employee.getUser(), employee))
        .collect(Collectors.toList());
        
        return employeeResponseDTOs;

    }
    
    @Override
    public EmployeeResponseDTO findByUsername(String username){
        Employee employee = employeeRepository.findByUserUsername(username);
        EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(employee.getUser(),employee);
        return employeeResponseDTO;
    }
    
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO newEmployee) {
        User newUserEntity = new User();
        newUserEntity.setAddress(newEmployee.getAddress());
        newUserEntity.setDpi(newEmployee.getDpi());
        newUserEntity.setEmail(newEmployee.getEmail());
        newUserEntity.setName(newEmployee.getName());
        newUserEntity.setNit(newEmployee.getNit());
        newUserEntity.setPassword(passwordEncoder.encode(newEmployee.getPassword()));
        newUserEntity.setPhone(newEmployee.getPhone());
        newUserEntity.setUsername(newEmployee.getUsername());

        if (newEmployee.getRole().startsWith("e") || newEmployee.getRole().startsWith("E")) {
            newUserEntity.setRole(Role.EMPLOYEE);
        } else if (newEmployee.getRole().startsWith("a") || newEmployee.getRole().startsWith("A")) {
            newUserEntity.setRole(Role.ADMINISTRATOR);
        }

        newUserEntity = this.userRepository.save(newUserEntity);


        Employee newEmployeeEntity = new Employee();
        newEmployeeEntity.setRole(newEmployee.getRole());
        newEmployeeEntity.setIdUser(newUserEntity.getIdUser());
//        newEmployeeEntity.setUser(newUserEntity);

        newEmployeeEntity = this.employeeRepository.save(newEmployeeEntity);

        return new EmployeeResponseDTO(newUserEntity,newEmployeeEntity);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(String id, EmployeeRequestDTO updatedEmployee) {
        Optional<User> optionalUser = userRepository.findByUsername(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Optional<Employee> optionalEmployee = employeeRepository.findById(user.getIdUser());
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.setRole(updatedEmployee.getRole());

                employee = employeeRepository.save(employee);

                user.setAddress(updatedEmployee.getAddress());
                user.setDpi(updatedEmployee.getDpi());
                user.setEmail(updatedEmployee.getEmail());
                user.setName(updatedEmployee.getName());
                user.setNit(updatedEmployee.getNit());
                if (updatedEmployee.getPassword().equals("")) {

                }else{
                    user.setPassword(passwordEncoder.encode(updatedEmployee.getPassword()));
                }
                
                
                user.setPhone(updatedEmployee.getPhone());

                user = userRepository.save(user);

                return new EmployeeResponseDTO(user,employee);
            }
            return null; // Is user but not employee
        } else {
            return null; // USer not found
        }
    }
}
