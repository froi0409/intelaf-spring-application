package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.orders.OrderHasProduct;
import com.ayd2.intelafbackend.entities.orders.OrderHasProductPK;
import com.ayd2.intelafbackend.projectioninterface.order.orderhasproducts.OrderHasProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHasProductRepository extends JpaRepository<OrderHasProduct, OrderHasProductPK> {

    @Query(value = "select ohp.product_id_product AS productId, ohp.quantity, p.name, p.price " +
            "FROM order_has_product ohp INNER JOIN product p " +
            "ON ohp.product_id_product = p.id_product " +
            "WHERE ohp.order_id_order = :idOrder", nativeQuery = true)
    List<OrderHasProductProjection> findProductsOrderByIdOrder(@Param("idOrder") Long idOrder);


}
