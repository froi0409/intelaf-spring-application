package com.ayd2.intelafbackend.projectioninterface.order.customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TrakingOrderProjection {
    Long getId_order();
    String getName_store_shipping();
    String getName_store_receive();
    LocalDateTime getDate_departure();
    // Add estimatedDeliveryDate as a method
    LocalDateTime getEstimatedDeliveryDate();
    BigDecimal getTotal();
    String getStatus();
}
