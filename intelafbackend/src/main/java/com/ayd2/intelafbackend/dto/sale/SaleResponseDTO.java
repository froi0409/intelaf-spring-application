package com.ayd2.intelafbackend.dto.sale;

import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.entities.users.Customer;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Value
public class SaleResponseDTO {

    Long idSale;
    LocalDateTime date;
    Double total;
    BigDecimal credit;
    String nit;

    public SaleResponseDTO(Sale sale){
        this.idSale = sale.getIdSale();
        this.date = sale.getDate();
        this.total = sale.getTotal();
        this.credit = sale.getCustomer().getCredit();
        this.nit = sale.getCustomer().getUser().getNit();
    }


}
