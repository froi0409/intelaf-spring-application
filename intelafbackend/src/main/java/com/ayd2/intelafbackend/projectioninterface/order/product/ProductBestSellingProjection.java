/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ayd2.intelafbackend.projectioninterface.order.product;

/**
 *
 * @author waliray
 */
public interface ProductBestSellingProjection {
    String getId_product();
    String getName();
    String getManufacturer();
    double getPrice();
    String getDescription();
    Integer getGuaranty_months();
    double getQuantity();
}
