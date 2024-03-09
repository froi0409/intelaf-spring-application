package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.store.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoreRepository extends CrudRepository<Store, String> {

    @Override
    List<Store> findAll();

}
