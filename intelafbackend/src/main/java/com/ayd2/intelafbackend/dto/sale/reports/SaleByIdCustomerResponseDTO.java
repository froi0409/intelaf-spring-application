/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ayd2.intelafbackend.dto.sale.reports;

import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResponseDTO;
import com.ayd2.intelafbackend.dto.sale.paymentsale.PaymentSaleResquestDTO;
import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.projectioninterface.sale.reports.SaleByIdCustomerProjection;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Value;

/**
 *
 * @author waliray
 */
@Value
public class SaleByIdCustomerResponseDTO {
    Long idSale;
    LocalDateTime date;
    Double total;        
    Long idCustomer;
    List<SaleHasProductReportResponseDTO> products;
    List<PaymentSaleResponseDTO> payments;

    public SaleByIdCustomerResponseDTO(Sale saleProjection ,List<SaleHasProductReportResponseDTO> products, List<PaymentSaleResponseDTO> payments) {
        this.idSale = saleProjection.getIdSale();
        this.date = saleProjection.getDate();
        this.total = saleProjection.getTotal();
        this.idCustomer = saleProjection.getCustomer().getUserIdUser();
        this.products = products;
        this.payments = payments;
    }
    
    
}
