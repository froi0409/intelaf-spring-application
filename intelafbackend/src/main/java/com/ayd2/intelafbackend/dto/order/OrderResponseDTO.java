package com.ayd2.intelafbackend.dto.order;

import com.ayd2.intelafbackend.entities.orders.Order;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class OrderResponseDTO {

    Long idOrder;
    String idStoreShipping;
    String idStoreReceive;
    LocalDateTime dateDeparture;
    LocalDateTime dateEntry;
    BigDecimal total;
    String status;

    public OrderResponseDTO(Order order) {
        this.idOrder = order.getIdOrder();
        this.idStoreReceive = order.getIdStoreReceive();
        this.idStoreShipping = order.getIdStoreShipping();
        this.dateDeparture = order.getDateDeparture();
        this.dateEntry = order.getDateEntry();
        this.total = order.getTotal();
        this.status = order.getStatus();
    }
}
