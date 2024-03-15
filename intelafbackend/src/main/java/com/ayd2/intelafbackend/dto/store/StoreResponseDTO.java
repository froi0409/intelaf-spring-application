package com.ayd2.intelafbackend.dto.store;

import com.ayd2.intelafbackend.entities.store.Store;
import lombok.Value;

@Value
public class StoreResponseDTO {

    String idStore;
    String name;
    String address;
    String phone1;
    String phone2;
    String email;
    String openingHour;
    String closingHour;

    public StoreResponseDTO(Store storeEntity) {
        this.idStore = storeEntity.getIdStore();
        this.name = storeEntity.getName();
        this.address = storeEntity.getAddress();
        this.phone1 = storeEntity.getPhone1();
        this.phone2 = storeEntity.getPhone2();
        this.email = storeEntity.getEmail();
        this.openingHour = storeEntity.getOpeningHour();
        this.closingHour = storeEntity.getClosingHour();
    }

}
