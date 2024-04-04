/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.sale.paymentsale;

import com.ayd2.intelafbackend.entities.sales.PaymentSale;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class PaymentSaleResponseDTO {
    private Long idPayment;
    private String type;
    private double amount;
    
    public PaymentSaleResponseDTO(PaymentSale paymentSale) {
        this.idPayment = paymentSale.getIdPayment();
        this.type = paymentSale.getType();
        this.amount = paymentSale.getAmount();
    }
}
