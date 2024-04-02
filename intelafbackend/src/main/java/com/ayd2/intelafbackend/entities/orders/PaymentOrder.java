package com.ayd2.intelafbackend.entities.orders;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Entity(name="payment_order")
@Getter
@Setter
@NoArgsConstructor
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_order")
    private Long idPayment;

    @Column( name = "type")
    private String type;

    @Column
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "order_id_order")
    private Order order;


}
