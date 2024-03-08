package com.ayd2.intelafbackend.dto.sale;

import com.ayd2.intelafbackend.entities.sales.Sale;
import com.ayd2.intelafbackend.entities.users.Customer;
import lombok.Value;

import java.util.Date;

@Value
public class SaleResponseDTO {

    Long idSale;
    Date date;
    Double total;
    Customer customer;

    public SaleResponseDTO(Sale sale){
        this.idSale = sale.getIdSale();
        this.date = sale.getDate();
        this.total = sale.getTotal();
        this.customer = sale.getCustomer();
    }


}
