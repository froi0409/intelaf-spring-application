package com.ayd2.intelafbackend.dto.order.reports;

import com.ayd2.intelafbackend.projectioninterface.order.customer.TrakingOrderProjection;
import com.ayd2.intelafbackend.projectioninterface.order.report.OrderInTimeStatusRouteProjection;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class OrderInTimeStatusRouteResponseDTO {
    Long idOrder;
    String nameStoreShipping;
    String nameStoreReceive;
    LocalDateTime dateDeparture;
    LocalDateTime estimatedDeliveryDate;
    BigDecimal total;
    String status;

    public OrderInTimeStatusRouteResponseDTO(OrderInTimeStatusRouteProjection orderInTimeStatusRoute){
        this.idOrder = orderInTimeStatusRoute.getId_order();
        this.nameStoreShipping = orderInTimeStatusRoute.getName_store_shipping();
        this.nameStoreReceive = orderInTimeStatusRoute.getName_store_receive();
        this.dateDeparture = orderInTimeStatusRoute.getDate_departure();
        this.estimatedDeliveryDate = orderInTimeStatusRoute.getEstimatedDeliveryDate();
        this.total = orderInTimeStatusRoute.getTotal();
        this.status = orderInTimeStatusRoute.getStatus();
    }
}
