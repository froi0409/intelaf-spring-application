package com.ayd2.intelafbackend.entities.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "store")
@Getter
@Setter
@NoArgsConstructor
public class Store {

    @Id
    @Column(name = "id_store")
    private String idStore;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone1;

    @Column
    private String phone2;

    @Column
    private String email;

    @Column(name = "opening_hour")
    private String openingHour;

    @Column(name = "closing_hour")
    private String closingHour;

}
