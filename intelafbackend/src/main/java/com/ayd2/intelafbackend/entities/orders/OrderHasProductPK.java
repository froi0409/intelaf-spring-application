package com.ayd2.intelafbackend.entities.orders;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderHasProductPK {

    @Column(name = "order_id_order")
    private Long orderId;

    @Column(name = "product_id_product")
    private String productId;

}
