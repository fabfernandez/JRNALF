package com.mercadolibre.finalchallengedemo.service;


import com.mercadolibre.finalchallengedemo.dtos.OrderRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;
import org.hibernate.criterion.Order;

import java.util.List;

public interface IOrderService {

    DealerOrderResponseDTO process(PartOrderQueryParamsDTO params);

    public DealerOrderResponseDTO getOrders(String dealerNumber, String deliveryStatus, Integer country, Integer order);
    public OrderStatusResponseDTO getOrdersFromDealersStatus(OrderStatusQueryParamsDTO orderStatusCMDTO);

    public List<DealerOrdersDTO> getAllOrders();

    OrderDetailsDTO createOrder(OrderRequestDTO order);

    String updateOrder(Integer orderNumber, Character orderStatus);

}
