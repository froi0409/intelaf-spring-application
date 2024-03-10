/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.employee;

import com.ayd2.intelafbackend.entities.users.Employee;
import com.ayd2.intelafbackend.entities.users.User;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class EmployeeResponseDTO {
    Long idUser;
    String nit;
    String name;
    String phone;
    String dpi;
    String email;
    String address;
    String username;
    String password;
    String role;

    public EmployeeResponseDTO(User user,Employee employee){
        this.idUser = user.getIdUser();
        this.nit = user.getNit();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.dpi = user.getDpi();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = employee.getRole();
    }
    
    
}
