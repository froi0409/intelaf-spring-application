package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.orders.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {

}
