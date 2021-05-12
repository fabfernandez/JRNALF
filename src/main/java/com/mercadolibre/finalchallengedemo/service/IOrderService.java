package com.mercadolibre.finalchallengedemo.service;


import com.mercadolibre.finalchallengedemo.dtos.OrderRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;

public interface IOrderService {

    DealerOrderResponseDTO getOrders(PartOrderQueryParamsDTO params);

    public DealerOrderResponseDTO getOrdersByDealerNumber(String dealerNumber, String deliveryStatus, Integer country, Integer order);
    public OrderStatusResponseDTO getOrdersFromDealersStatus(OrderStatusQueryParamsDTO orderStatusCMDTO);

    OrderDetailsDTO createOrder(OrderRequestDTO order);

    void updateOrder(Integer orderNumber, Character orderStatus);

}
