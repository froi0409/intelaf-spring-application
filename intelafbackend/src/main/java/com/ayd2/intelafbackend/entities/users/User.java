package com.ayd2.intelafbackend.entities.users;

import com.ayd2.intelafbackend.enums.user.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long idUser;

    @Column(name = "nit", nullable = false)
    private String nit;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 8)
    private String phone;

    @Column(name = "dpi", length = 13)
    private String dpi;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;
}
