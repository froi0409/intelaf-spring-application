package com.ayd2.intelafbackend.dto.store;

import com.ayd2.intelafbackend.entities.store.Store;
import lombok.Value;

import java.util.Date;

@Value
public class EditStoreRequestDTO {
    String name;
    String address;
    String phone1;
    String phone2;
    String email;
    Date openingHour;
    Date closingHour;

    public EditStoreRequestDTO(Store storeEntity) {
        this.name = storeEntity.getName();
        this.address = storeEntity.getAddress();
        this.phone1 = storeEntity.getPhone1();
        this.phone2 = storeEntity.getPhone2();
        this.email = storeEntity.getEmail();
        this.openingHour = storeEntity.getOpeningHour();
        this.closingHour = storeEntity.getClosingHour();
    }

}
