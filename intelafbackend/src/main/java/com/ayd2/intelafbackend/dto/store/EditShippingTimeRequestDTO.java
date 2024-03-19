package com.ayd2.intelafbackend.dto.store;

import lombok.Value;

@Value
public class EditShippingTimeRequestDTO {
    String idStore1;
    String idStore2;
    Integer time;
}
