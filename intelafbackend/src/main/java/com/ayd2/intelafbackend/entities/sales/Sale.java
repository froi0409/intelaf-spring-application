package com.ayd2.intelafbackend.entities.sales;

import com.ayd2.intelafbackend.entities.users.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sale")
    private Long idSale;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "user_id_user")
    private Customer customer;

}
