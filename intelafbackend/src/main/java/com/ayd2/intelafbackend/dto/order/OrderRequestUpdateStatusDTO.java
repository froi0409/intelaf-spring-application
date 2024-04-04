package com.ayd2.intelafbackend.dto.order;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class OrderRequestUpdateStatusDTO {
    Long idOrder;
    String status;
    LocalDateTime dateEntry;
}
