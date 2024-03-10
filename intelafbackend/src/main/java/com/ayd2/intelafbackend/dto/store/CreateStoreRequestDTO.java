package com.ayd2.intelafbackend.dto.store;

import lombok.Value;

@Value
public class CreateStoreRequestDTO {
    String idStore;
    String name;
    String address;
    String phone1;
    String email;
    String phone2;
    String storeHours;
}
