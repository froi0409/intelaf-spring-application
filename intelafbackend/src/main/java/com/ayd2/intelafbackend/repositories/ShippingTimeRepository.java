package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.store.ShippingTime;
import com.ayd2.intelafbackend.entities.store.ShippingTimeId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ShippingTimeRepository extends CrudRepository<ShippingTime, ShippingTimeId> {
    List<ShippingTime> findAll();

    List<ShippingTime> findAllByIdStore1(String idStore1);

    List<ShippingTime> findAllByIdStore2(String idStore2);
}
