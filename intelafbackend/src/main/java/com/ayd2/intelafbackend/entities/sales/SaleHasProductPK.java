package com.ayd2.intelafbackend.entities.sales;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class SaleHasProductPK implements Serializable {

    @Column(name = "sale_id_sale")
    private Long saleId;

    @Column(name = "product_id_product")
    private String productId;

}
