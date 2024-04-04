package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.dto.order.deliveryorder.DeliveryOrderResponseDTO;
import com.ayd2.intelafbackend.entities.orders.Order;
import com.ayd2.intelafbackend.projectioninterface.order.DeliveryOrderProjection;
import com.ayd2.intelafbackend.projectioninterface.order.customer.TrakingOrderProjection;
import com.ayd2.intelafbackend.projectioninterface.order.reports.OrderByCustomerProjection;
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
            "o.total, o.status, u.nit " +
            "FROM intelafdb.order o " +
            "INNER JOIN shipping_time st ON " +
            "(o.id_store_shipping = st.id_store1 AND o.id_store_receive = st.id_store2) OR " +
            "(o.id_store_shipping = st.id_store2 AND o.id_store_receive = st.id_store1) " +
            "INNER JOIN user u ON " +
            "u.id_user = o.id_customer " +
            "WHERE o.id_order = :idOrder " , nativeQuery = true)
    Optional<DeliveryOrderProjection> findByIdWithEstimateDelivery(@Param("idOrder") Long idOrder);

    @Query(value = "SELECT o.id_order, ss.name as name_store_shipping, sr.name as name_store_receive, o.date_departure, " +
            "DATE_ADD(o.date_departure, INTERVAL st.time DAY) AS estimatedDeliveryDate, " +
            "o.total, o.status " +
            "FROM intelafdb.order o " +
            "INNER JOIN shipping_time st ON " +
            "(o.id_store_shipping = st.id_store1 AND o.id_store_receive = st.id_store2) OR " +
            "(o.id_store_shipping = st.id_store2 AND o.id_store_receive = st.id_store1) " +
            "INNER JOIN store ss ON (ss.id_store = o.id_store_shipping) " +
            "INNER JOIN store sr ON (sr.id_store = o.id_store_receive) " +
            "INNER JOIN user u ON " +
            "u.id_user = o.id_customer " +
            "WHERE o.id_customer = :idCustomer " , nativeQuery = true)
    List<TrakingOrderProjection> findOrdersByCustomerId(@Param("idCustomer") Long idCustomer);
    
    @Query(value = 
            "SELECT o.id_order, o.id_store_shipping, o.id_store_receive, o.date_departure, o.date_entry, o.total, o.status, u.nit " +
            "FROM `order` o " +
            "INNER JOIN user u ON u.id_user = o.id_customer " +
            "WHERE o.id_customer = :idCustomer " +
            "AND o.status = 'Route';" , nativeQuery = true)
    List<OrderByCustomerProjection> findAllOrdersByCustomerId(@Param("idCustomer") Long idCustomer);

    @Override
    Optional<Order> findById(Long id);

}
