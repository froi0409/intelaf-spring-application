package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.dto.order.deliveryorder.DeliveryOrderResponseDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.projectioninterface.order.DeliveryOrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT o.id_order, o.id_store_shipping, o.id_store_receive, o.date_departure, o.date_entry, " +
            "DATE_ADD(o.date_departure, INTERVAL st.time DAY) AS estimatedDeliveryDate, " +
            "o.total, o.status " +
            "FROM intelafdb.order o " +
            "INNER JOIN shipping_time st ON " +
            "(o.id_store_shipping = st.id_store1 AND o.id_store_receive = st.id_store2) OR " +
            "(o.id_store_shipping = st.id_store2 AND o.id_store_receive = st.id_store1) " +
            "WHERE o.id_store_receive = :idStoreReceive " +
            "ORDER BY o.id_order ASC", nativeQuery = true)
    List<DeliveryOrderProjection> findDeliveryOrdersByReceiveStore(@Param("idStoreReceive") String idStoreReceive);

    @Query(value = "SELECT o.id_order, o.id_store_shipping, o.id_store_receive, o.date_departure, o.date_entry, " +
            "DATE_ADD(o.date_departure, INTERVAL st.time DAY) AS estimatedDeliveryDate, " +
            "o.total, o.status " +
            "FROM intelafdb.order o " +
            "INNER JOIN shipping_time st ON " +
            "(o.id_store_shipping = st.id_store1 AND o.id_store_receive = st.id_store2) OR " +
            "(o.id_store_shipping = st.id_store2 AND o.id_store_receive = st.id_store1) " +
            "WHERE o.id_store_shipping = :idStoreShipping " +
            "ORDER BY o.id_order ASC", nativeQuery = true)
    List<DeliveryOrderProjection> findDeliveryOrdersByShippingStore(@Param("idStoreShipping") String idStoreShipping);

    @Query(value = "SELECT o.id_order, o.id_store_shipping, o.id_store_receive, o.date_departure, o.date_entry, " +
            "DATE_ADD(o.date_departure, INTERVAL st.time DAY) AS estimatedDeliveryDate, " +
            "o.total, o.status " +
            "FROM intelafdb.order o " +
            "INNER JOIN shipping_time st ON " +
            "(o.id_store_shipping = st.id_store1 AND o.id_store_receive = st.id_store2) OR " +
            "(o.id_store_shipping = st.id_store2 AND o.id_store_receive = st.id_store1) " +
            "WHERE o.id_order = :idOrder " , nativeQuery = true)
    Optional<DeliveryOrderProjection> findByIdWithEstimateDelivery(@Param("idOrder") Long idOrder);

    @Override
    Optional<Order> findById(Long id);

}
