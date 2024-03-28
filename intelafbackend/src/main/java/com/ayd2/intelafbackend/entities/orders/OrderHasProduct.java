package com.ayd2.intelafbackend.entities.orders;

import com.ayd2.intelafbackend.entities.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "order_has_product")
@Getter
@Setter
@NoArgsConstructor
public class OrderHasProduct {

    @EmbeddedId
    private OrderHasProductPK orderHasProductPK;

    @Column(name = "quantity")
    private Integer quantiy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id_order", nullable = false, insertable = false, updatable = false)
    private Order order;

    @ManyToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "product_id_product", nullable = false, insertable = false, updatable = false)
    private Product product;
}
