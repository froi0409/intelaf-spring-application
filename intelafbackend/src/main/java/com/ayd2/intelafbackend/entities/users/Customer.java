package com.ayd2.intelafbackend.entities.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @Column
    private Integer idUser;

    @Column
    private String nit;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String dpi;

    @Column
    private String email;

    @Column
    private String address;

    @Column
    private String password;

    @Column
    private String username;


}
