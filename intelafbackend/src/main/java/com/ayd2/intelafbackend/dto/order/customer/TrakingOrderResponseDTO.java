package com.ayd2.intelafbackend.dto.order.customer;
import com.ayd2.intelafbackend.projectioninterface.order.customer.TrakingOrderProjection;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class TrakingOrderResponseDTO {
    Long idOrder;
    String nameStoreShipping;
    String nameStoreReceive;
    LocalDateTime dateDeparture;
    LocalDateTime estimatedDeliveryDate;
    BigDecimal total;
    String status;

    public TrakingOrderResponseDTO(TrakingOrderProjection trakingOrderProjection){
        this.idOrder = trakingOrderProjection.getId_order();
        this.nameStoreShipping = trakingOrderProjection.getName_store_shipping();
        this.nameStoreReceive = trakingOrderProjection.getName_store_receive();
        this.dateDeparture = trakingOrderProjection.getDate_departure();
        this.estimatedDeliveryDate = trakingOrderProjection.getEstimatedDeliveryDate();
        this.total = trakingOrderProjection.getTotal();
        this.status = trakingOrderProjection.getStatus();
    }
}
