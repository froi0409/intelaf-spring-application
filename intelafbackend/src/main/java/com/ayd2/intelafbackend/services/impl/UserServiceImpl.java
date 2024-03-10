package com.ayd2.intelafbackend.services.impl;

import com.ayd2.intelafbackend.dto.customer.CustomerResponseDTO;
import com.ayd2.intelafbackend.dto.employee.EmployeeRequestDTO;
import com.ayd2.intelafbackend.dto.employee.EmployeeResponseDTO;
import com.ayd2.intelafbackend.dto.user.UserResponseDTO;
import com.ayd2.intelafbackend.entities.users.Employee;
import com.ayd2.intelafbackend.entities.users.User;
import com.ayd2.intelafbackend.repositories.EmployeeRepository;
import com.ayd2.intelafbackend.repositories.UserRepository;
import com.ayd2.intelafbackend.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDTO:: new)
                .collect(Collectors.toList());
    }
    
    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO newEmployee) {
        User newUserEntity = new User();
        newUserEntity.setAddress(newEmployee.getAddress());
        newUserEntity.setDpi(newEmployee.getDpi());
        newUserEntity.setEmail(newEmployee.getEmail());
        newUserEntity.setName(newEmployee.getEmail());
        newUserEntity.setNit(newEmployee.getNit());
        newUserEntity.setPassword(newEmployee.getPassword());
        newUserEntity.setPhone(newEmployee.getPhone());
        newUserEntity.setUsername(newEmployee.getUsername());
                
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
                user.setPassword(updatedEmployee.getPassword());
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
