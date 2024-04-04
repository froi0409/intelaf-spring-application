package com.ayd2.intelafbackend.dto.customer.update;

import com.ayd2.intelafbackend.entities.users.Customer;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CustomerUpdateResponseDTO {

    Long userIdUser;
    BigDecimal credit;
    String nit;
    String name;
    String phone;
    String dpi;
    String email;
    String address;
    String username;

    public CustomerUpdateResponseDTO(Customer customer){
        this.userIdUser = customer.getUserIdUser();
        this.credit = customer.getCredit();
        this.nit = customer.getUser().getNit();
        this.name = customer.getUser().getName();
        this.phone = customer.getUser().getPhone();
        this.dpi = customer.getUser().getDpi();
        this.email = customer.getUser().getEmail();
        this.address = customer.getUser().getAddress();
        this.username = customer.getUser().getUsername();
    }

}
