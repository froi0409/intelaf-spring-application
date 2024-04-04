package com.ayd2.intelafbackend.dto.order.deliveryorder;

import com.ayd2.intelafbackend.projectioninterface.order.DeliveryOrderProjection;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class DeliveryOrderResponseDTO {
    Long idOrder;
    String idStoreShipping;
    String idStoreReceive;
    LocalDateTime dateDeparture;
    LocalDateTime dateEntry;
    LocalDateTime estimatedDeliveryDate;
    BigDecimal total;
    String status;


    public DeliveryOrderResponseDTO(DeliveryOrderProjection deliveryOrderProjection){
        this.idOrder = deliveryOrderProjection.getId_order();
        this.idStoreShipping = deliveryOrderProjection.getId_store_shipping();
        this.idStoreReceive = deliveryOrderProjection.getId_store_receive();
        this.dateDeparture = deliveryOrderProjection.getDate_departure();
        this.dateEntry = deliveryOrderProjection.getDate_entry();
        this.estimatedDeliveryDate = deliveryOrderProjection.getEstimatedDeliveryDate();
        this.total = deliveryOrderProjection.getTotal();
        this.status = deliveryOrderProjection.getStatus();
    }

}
