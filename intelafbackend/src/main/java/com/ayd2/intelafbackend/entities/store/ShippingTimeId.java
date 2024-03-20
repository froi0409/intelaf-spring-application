package com.ayd2.intelafbackend.entities.store;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ShippingTimeId implements Serializable {
    private String idStore1;
    private String idStore2;
}
