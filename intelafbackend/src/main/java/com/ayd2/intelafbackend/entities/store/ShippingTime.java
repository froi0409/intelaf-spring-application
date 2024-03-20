package com.ayd2.intelafbackend.entities.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass(ShippingTimeId.class)
@Getter
@Setter
@NoArgsConstructor
public class ShippingTime {
    @Id
    @Column(name = "id_store1")
    private String idStore1;

    @Id
    @Column(name = "id_store2")
    private String idStore2;

    @Column
    private Integer time;
}
