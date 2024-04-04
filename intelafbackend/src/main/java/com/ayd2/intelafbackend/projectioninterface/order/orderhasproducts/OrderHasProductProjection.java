package com.ayd2.intelafbackend.projectioninterface.order.orderhasproducts;

public interface OrderHasProductProjection {
        String getProductId();
        Integer getQuantity();
        String getName();
        Double getPrice();

}
