package com.ayd2.intelafbackend.dto.store;

import lombok.Value;

import java.util.Date;

@Value
public class EditStoreRequestDTO {
    String idStore;
    String name;
    String address;
    String phone1;
    String phone2;
    String email;
    String openingHour;
    String closingHour;
}
