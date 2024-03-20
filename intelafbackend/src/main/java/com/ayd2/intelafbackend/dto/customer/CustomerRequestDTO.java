package com.ayd2.intelafbackend.dto.customer;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CustomerRequestDTO {

    String nit;
    String name;
    String phone;
    String dpi;
    String email;
    String address;
    String password;
    String username;
    BigDecimal credit;

}
