package com.ayd2.intelafbackend.entities.orders;


import com.ayd2.intelafbackend.entities.store.Store;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "id_store_shipping", nullable = false)
    private String idStoreShipping;

    @Column(name = "id_store_receive", nullable = false)
    private String idStoreReceive;

    @Column(name = "date_departure", nullable = false)
    private LocalDateTime dateDeparture;

    @Column(name = "date_entry")
    private LocalDateTime dateEntry;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "status", length = 45)
    private String status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_store_shipping", referencedColumnName = "id_store", insertable = false, updatable = false)
    private Store shippingStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_store_receive", referencedColumnName = "id_store", insertable = false, updatable = false)
    private Store receivingStore;

}
