package com.ayd2.intelafbackend.dto.order.reports;

import com.ayd2.intelafbackend.entities.products.Product;
import lombok.Value;

import java.util.List;

@Value
public class OrdersThatWillArriveResponseDTO {
    String idOrder;
    List<Product> productList;
    String status;
}
