package com.ayd2.intelafbackend.dto.order.reports;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetail {
    private Long orderId;
    private String productDescription;
    private Double total;
    private String status;
}

