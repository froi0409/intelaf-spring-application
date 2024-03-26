package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.orders.OrderHasProduct;
import com.ayd2.intelafbackend.entities.orders.OrderHasProductPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHasProductRepository extends JpaRepository<OrderHasProduct, OrderHasProductPK> {
}
