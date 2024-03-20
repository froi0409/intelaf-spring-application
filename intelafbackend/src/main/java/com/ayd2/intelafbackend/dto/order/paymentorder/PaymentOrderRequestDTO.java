package com.ayd2.intelafbackend.dto.order.paymentorder;

import lombok.Value;

@Value
public class PaymentOrderRequestDTO {
    String type;
    double amount;
}
