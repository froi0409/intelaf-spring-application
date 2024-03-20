package com.ayd2.intelafbackend.dto.order.orderhasproducts;

import lombok.Value;

@Value
public class OrderHasProductRequestDTO {
    String productId;
    Integer quantity;
}
