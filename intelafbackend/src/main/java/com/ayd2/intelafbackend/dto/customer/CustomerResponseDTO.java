package com.ayd2.intelafbackend.dto.customer;

import com.ayd2.intelafbackend.entities.users.Customer;
import com.ayd2.intelafbackend.entities.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class CustomerResponseDTO {

     Long userIdUser;
     BigDecimal credit;
     User user;

     public CustomerResponseDTO(Customer customer){
         this.userIdUser = customer.getUserIdUser();
         this.credit = customer.getCredit();
         this.user = customer.getUser();
     }
}
