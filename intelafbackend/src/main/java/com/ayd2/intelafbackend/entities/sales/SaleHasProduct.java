package com.ayd2.intelafbackend.entities.sales;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SaleHasProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id_sale")
    private int saleId;

    @Id
    @Column(name = "product_id_product", length = 10)
    private String productId;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY) // Optional: Lazy loading for performance
    @JoinColumn(name = "sale_id_sale", nullable = false, insertable = false, updatable = false)
    private Sale sale; // Mapped to the "sale" table with a foreign key

//    @ManyToOne(fetch = FetchType.LAZY) // Optional: Lazy loading for performance
//    @JoinColumn(name = "product_id_product", nullable = false, insertable = false, updatable = false)
//    private Product product; // Mapped to the "product" table with a foreign key
}
