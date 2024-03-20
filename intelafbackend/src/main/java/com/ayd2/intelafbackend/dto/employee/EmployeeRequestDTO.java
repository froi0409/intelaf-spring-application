/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.employee;

import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class EmployeeRequestDTO {
    
    final private Long idUser;
    final private String nit;
    final private String name;
    final private String phone;
    final private String dpi;
    final private String email;
    final private String address;
    final private String username;
    final private String password;
    final private String role;
    
}
