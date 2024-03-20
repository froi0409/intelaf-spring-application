package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
