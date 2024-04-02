package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.orders.PaymentOrder;
import com.ayd2.intelafbackend.projectioninterface.order.paymentorder.PaymentOrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {

    @Query(value = "SELECT id_payment_order AS idPaymentOrder, type, amount FROM payment_order WHERE order_id_order = :orderIdOrder", nativeQuery = true)
    List<PaymentOrderProjection> findPaymentOrdersByOrderId(@Param("orderIdOrder") Long orderIdOrder);
}
