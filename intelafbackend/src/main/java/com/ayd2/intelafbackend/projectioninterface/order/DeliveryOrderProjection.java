package com.ayd2.intelafbackend.projectioninterface.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface DeliveryOrderProjection {
    Long getId_order();
    String getId_store_shipping();
    String getId_store_receive();
    LocalDateTime getDate_departure();
    LocalDateTime getDate_entry();
    // Add estimatedDeliveryDate as a method
    LocalDateTime getEstimatedDeliveryDate();
    BigDecimal getTotal();
    String getStatus();
}
