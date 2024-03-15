package com.ayd2.intelafbackend.entities.sales;

import com.ayd2.intelafbackend.entities.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "payment_sale")
@Getter
@Setter
@NoArgsConstructor
public class PaymentSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_payment_sale")
    private Long idPayment;

    @Column(name = "type", length = 45)
    private String type;

    @Column(name = "amount")
    private double amount;

    @ManyToOne // Optional: Lazy loading for performance
    @JoinColumn(name = "sale_id_sale")
    private Sale sale; // Mapped to the "sale" table with a foreign key


}
