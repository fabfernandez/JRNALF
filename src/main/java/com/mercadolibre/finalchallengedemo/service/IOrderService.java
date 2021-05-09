package com.mercadolibre.finalchallengedemo.service;


import com.mercadolibre.finalchallengedemo.dtos.orderstatus.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.PartOrderQueryParamsDTO;

public interface IOrderService {

    DealerOrderResponseDTO getOrders(PartOrderQueryParamsDTO params);

    public DealerOrderResponseDTO getOrdersByDealerNumber(String dealerNumber, String deliveryStatus, Integer country, Integer order);
    public OrderStatusResponseDTO getOrdersFromDealersStatus(OrderStatusQueryParamsDTO orderStatusCMDTO);
}
