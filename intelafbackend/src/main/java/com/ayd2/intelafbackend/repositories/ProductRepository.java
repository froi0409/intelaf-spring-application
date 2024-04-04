/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.repositories;

import com.ayd2.intelafbackend.entities.products.Product;
import com.ayd2.intelafbackend.projectioninterface.order.product.ProductBestSellingProjection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author waliray
 */
public interface ProductRepository extends CrudRepository<Product, String>{
    @Override
    List<Product> findAll();
        
    @Override
    Optional<Product> findById(String id);
    
    @Query(value = "SELECT p.id_product, p.name, p.manufacturer, p.price, p.description, p.guaranty_months, SUM(shp.quantity) AS quantity\n" +
            "FROM sale s\n" +
            "JOIN sale_has_product shp ON s.id_sale = shp.sale_id_sale\n" +
            "JOIN product p ON shp.product_id_product = p.id_product\n" +
            "WHERE s.date >= :date1 AND s.date <= :date2\n" +
            "GROUP BY p.id_product, p.name\n" +
            "ORDER BY quantity DESC\n" +
            "LIMIT 10;", nativeQuery = true)
    List<ProductBestSellingProjection> findBestSellingProducts( @Param("date1") LocalDate date1, @Param("date2") LocalDate date2);

    @Query(value = "SELECT p.id_product, p.name, p.price " +
            "FROM product p " +
            "LEFT JOIN sale_has_product sp ON p.id_product = sp.product_id_product " +
            "WHERE sp.product_id_product IS NULL", nativeQuery = true)
    List<Object[]> findProductsNeverSold();

}
