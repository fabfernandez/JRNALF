package com.mercadolibre.finalchallengedemo.service;


import com.mercadolibre.finalchallengedemo.dtos.OrderRequestDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.*;

public interface IOrderService {

    DealerOrderResponseDTO process(PartOrderQueryParamsDTO params);

    public DealerOrderResponseDTO getOrders(String dealerNumber, String deliveryStatus, Integer country, Integer order);
    public OrderStatusResponseDTO getOrdersFromDealersStatus(OrderStatusQueryParamsDTO orderStatusCMDTO);

    OrderDetailsDTO createOrder(OrderRequestDTO order);

    String updateOrder(Integer orderNumber, Character orderStatus);

}
