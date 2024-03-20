package com.ayd2.intelafbackend.dto.customer;

import com.ayd2.intelafbackend.entities.users.Customer;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CustomerResponseDTO {

    Long userIdUser;
    BigDecimal credit;
    String nit;
    String name;
    String phone;
    String dpi;
    String email;
    String address;

     public CustomerResponseDTO(Customer customer){
         this.userIdUser = customer.getUserIdUser();
         this.credit = customer.getCredit();
         this.nit = customer.getUser().getNit();
         this.name = customer.getUser().getName();
         this.phone = customer.getUser().getPhone();
         this.dpi = customer.getUser().getDpi();
         this.email = customer.getUser().getEmail();
         this.address = customer.getUser().getAddress();
     }
}
