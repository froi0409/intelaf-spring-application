package com.ayd2.intelafbackend.dto.sale;

import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.dto.sale.salehasproduct.SaleHasProductRequestDTO;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class SaleOrderRequestDTO {
    LocalDateTime date;
    Double total;
    String nit;
    SaleHasProductRequestDTO[] products;
    PaymentSaleResquestDTO[] payments;
    String storeCode;
    LocalDateTime dateEntry;
    LocalDateTime estimatedDeliveryDate;
}
