/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.entities.employees;

import com.ayd2.intelafbackend.entities.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author waliray
 */
@Entity(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    private User user;

    // Otros campos y métodos según sea necesario

    public Employee(Long idUser, String role) {
        this.idUser = idUser;
        this.role = role;
    }
}
