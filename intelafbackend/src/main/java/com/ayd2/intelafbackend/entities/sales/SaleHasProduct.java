package com.ayd2.intelafbackend.entities.sales;

import com.ayd2.intelafbackend.entities.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "sale_has_product")
@Getter
@Setter
@NoArgsConstructor
public class SaleHasProduct {

    @EmbeddedId
    private SaleHasProductPK id;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY) // Optional: Lazy loading for performance
    @JoinColumn(name = "sale_id_sale", nullable = false, insertable = false, updatable = false)
    private Sale sale; // Mapped to the "sale" table with a foreign key

    @ManyToOne(fetch = FetchType.LAZY) // Optional: Lazy loading for performance
    @JoinColumn(name = "product_id_product", nullable = false, insertable = false, updatable = false)
    private Product product; // Mapped to the "product" table with a foreign key
}
