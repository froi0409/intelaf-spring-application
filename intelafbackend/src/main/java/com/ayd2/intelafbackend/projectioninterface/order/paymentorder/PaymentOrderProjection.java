package com.ayd2.intelafbackend.projectioninterface.order.paymentorder;

public interface PaymentOrderProjection {
    Long getIdPaymentOrder();
    String getType();
    Double getAmount();
}
