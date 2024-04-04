package com.ayd2.intelafbackend.projectioninterface.order.report;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface OrderInTimeStatusRouteProjection {

    Long getId_order();
    String getName_store_shipping();
    String getName_store_receive();
    LocalDateTime getDate_departure();
    LocalDateTime getEstimatedDeliveryDate();
    BigDecimal getTotal();
    String getStatus();
}
