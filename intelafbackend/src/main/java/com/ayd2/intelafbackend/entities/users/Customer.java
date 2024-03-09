package com.ayd2.intelafbackend.entities.users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @Column(name = "user_id_user")
    private Long userIdUser;

    @Column(name = "credit")
    private BigDecimal credit;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id_user")
    private User user;
}