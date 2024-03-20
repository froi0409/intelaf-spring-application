package com.ayd2.intelafbackend.controllers;

import com.ayd2.intelafbackend.dto.employee.EmployeeRequestDTO;
import com.ayd2.intelafbackend.dto.employee.EmployeeResponseDTO;
import com.ayd2.intelafbackend.dto.user.UserResponseDTO;
import com.ayd2.intelafbackend.services.EmployeeService;
import com.ayd2.intelafbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/v1/user")
public class UserController {

    private UserService userService;
    private EmployeeService employeeService;

    @Autowired
    public UserController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping("all")
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        return  ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
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
        EmployeeResponseDTO responseDTO = userService.createEmployee(newEmployee);
        
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }
    
    @PutMapping("/update-employee/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable("id") String id, @RequestBody EmployeeRequestDTO updatedEmployee) {
        EmployeeResponseDTO responseDTO = userService.updateEmployee(id, updatedEmployee);
        if (responseDTO != null) {
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
