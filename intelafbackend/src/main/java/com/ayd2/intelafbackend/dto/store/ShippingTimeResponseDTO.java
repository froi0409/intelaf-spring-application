package com.ayd2.intelafbackend.dto.store;

import com.ayd2.intelafbackend.entities.store.ShippingTime;
import lombok.Value;

@Value
public class ShippingTimeResponseDTO {
    String idStore1;
    String idStore2;
    Integer time;

    public ShippingTimeResponseDTO(ShippingTime shippingTime) {
        this.idStore1 = shippingTime.getIdStore1();
        this.idStore2 = shippingTime.getIdStore2();
        this.time = shippingTime.getTime();
    }
}
