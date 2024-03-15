package com.ayd2.intelafbackend.dto.sale.salehasproduct;

import lombok.Value;

@Value
public class SaleHasProductRequestDTO {
    String productId;
    Integer quantity;
}
