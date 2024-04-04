package com.ayd2.intelafbackend.dto.user;

import com.ayd2.intelafbackend.dto.customer.CustomerRequestDTO;
import com.ayd2.intelafbackend.enums.user.Role;
import lombok.Value;

@Value
public class UserRequestDTO {

    String nit;
    String name;
    String phone;
    String dpi;
    String email;
    String address;
    String password;
    String username;
    Role role;


    public UserRequestDTO(CustomerRequestDTO customerRequestDTO){
        this.nit = customerRequestDTO.getNit();
        this.name = customerRequestDTO.getName();
        this.phone = customerRequestDTO.getPhone();
        this.dpi = customerRequestDTO.getDpi();
        this.email = customerRequestDTO.getEmail();
        this.address = customerRequestDTO.getAddress();
        this.password = customerRequestDTO.getPassword();
        this.username = customerRequestDTO.getUsername();
        this.role = Role.CUSTOMER;
    }

}
