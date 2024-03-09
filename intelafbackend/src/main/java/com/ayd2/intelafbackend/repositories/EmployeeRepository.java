/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.users.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author waliray
 */
public interface EmployeeRepository extends CrudRepository<Employee,Long>{
    
}
