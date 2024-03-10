package com.ayd2.intelafbackend.dto.sale;

import com.ayd2.intelafbackend.entities.users.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Value;

import java.util.Date;

@Value
public class SaleRequestDTO {
    Date date;
    Double total;
    Integer customer;
}