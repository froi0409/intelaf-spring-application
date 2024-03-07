package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.sales.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends CrudRepository<Sale, Long> {

}
