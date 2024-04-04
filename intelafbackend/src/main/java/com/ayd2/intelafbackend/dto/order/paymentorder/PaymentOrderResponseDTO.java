package com.ayd2.intelafbackend.dto.order.paymentorder;

import com.ayd2.intelafbackend.projectioninterface.order.paymentorder.PaymentOrderProjection;
import lombok.Value;

@Value
public class PaymentOrderResponseDTO {

    String type;
    double amount;

    public PaymentOrderResponseDTO(PaymentOrderProjection paymentOrderProjection){
        this.type = paymentOrderProjection.getType();
        this.amount = paymentOrderProjection.getAmount();
    }
}
