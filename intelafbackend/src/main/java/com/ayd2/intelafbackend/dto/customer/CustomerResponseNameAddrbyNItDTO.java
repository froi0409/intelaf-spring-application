package com.ayd2.intelafbackend.dto.customer;


import com.ayd2.intelafbackend.entities.users.Customer;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CustomerResponseNameAddrbyNItDTO {

    String nit;
    String name;
    String address;
    BigDecimal credit;

    public CustomerResponseNameAddrbyNItDTO(Customer customer) {
        this.nit = customer.getUser().getNit();
        this.name = customer.getUser().getName();
        this.address = customer.getUser().getAddress();
        this.credit = customer.getCredit();
    }
}
