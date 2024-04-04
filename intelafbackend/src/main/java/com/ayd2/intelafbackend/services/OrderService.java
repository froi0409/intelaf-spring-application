package com.ayd2.intelafbackend.services;

import com.ayd2.intelafbackend.dto.order.OrderRequestDTO;
import com.ayd2.intelafbackend.dto.order.OrderRequestUpdateStatusDTO;
import com.ayd2.intelafbackend.dto.order.OrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.customer.TrakingOrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.deliveryorder.DeliveryOrderResponseDTO;
import com.ayd2.intelafbackend.dto.order.deliveryorder.OrderAllFeatureResponseDTO;
import com.ayd2.intelafbackend.dto.order.reports.OrderDetail;
import com.ayd2.intelafbackend.dto.order.reports.OrderInTimeStatusRouteResponseDTO;
import com.ayd2.intelafbackend.dto.order.reports.OrderReportResponseDTO;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import com.ayd2.intelafbackend.exceptions.NotAcceptableException;

import java.util.List;

public interface OrderService {
    List<OrderResponseDTO> findAll();

    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) throws EntityNotFoundException, NotAcceptableException;

    List<DeliveryOrderResponseDTO> findDeliveryOrdersByReceiveStore(String idStoreReceive);

    List<DeliveryOrderResponseDTO> findDeliveryOrdersByShippingStore(String idStoreShipping);

    OrderResponseDTO updateStatus(OrderRequestUpdateStatusDTO orderRequestUpdateStatusDTO) throws EntityNotFoundException;

    OrderResponseDTO findById(Long idOrder) throws EntityNotFoundException;

    OrderAllFeatureResponseDTO findByIdWithEstimateDelivery(Long idOrder) throws EntityNotFoundException;

    List<TrakingOrderResponseDTO> findOrdersByCustomerId(Long idCustomer) throws EntityNotFoundException;

    List<OrderReportResponseDTO> orderByIdCustomer(Long idCustomer);

    List<TrakingOrderResponseDTO> findOrdersByCustomerUsername(String userUsername) throws EntityNotFoundException;

    List<OrderInTimeStatusRouteResponseDTO> reportInTimeWithPendingVerification(String idStoreReceive) throws EntityNotFoundException;

    List<OrderInTimeStatusRouteResponseDTO> reportOverdueArrivingStore(String idStoreReceive) throws EntityNotFoundException;

    List<OrderInTimeStatusRouteResponseDTO> reportLeavingStoreInTransit(String idStoreReceive) throws EntityNotFoundException;

    List<OrderDetail> reportPackagesThatWillArriveAtAStore(String idStore) throws EntityNotFoundException;
}
