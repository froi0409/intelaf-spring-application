package com.ayd2.intelafbackend.dto.store;

import lombok.Value;

import java.util.Date;

@Value
public class CreateStoreRequestDTO {
    String idStore;
    String name;
    String address;
    String phone1;
    String email;
    String phone2;
    Date openingHour;
    Date closingHour;
}
