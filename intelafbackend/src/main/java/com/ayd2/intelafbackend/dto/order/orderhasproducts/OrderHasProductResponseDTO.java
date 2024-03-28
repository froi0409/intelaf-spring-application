package com.ayd2.intelafbackend.dto.order.orderhasproducts;

import com.ayd2.intelafbackend.projectioninterface.order.orderhasproducts.OrderHasProductProjection;
import lombok.Value;

@Value
public class OrderHasProductResponseDTO {

    String productId;
    Integer quantity;
    String name;
    Double price;

    public OrderHasProductResponseDTO(OrderHasProductProjection orderHasProductProjection){
        this.productId = orderHasProductProjection.getProductId();
        this.quantity = orderHasProductProjection.getQuantity();
        this.name = orderHasProductProjection.getName();
        this.price = orderHasProductProjection.getPrice();
    }
}
