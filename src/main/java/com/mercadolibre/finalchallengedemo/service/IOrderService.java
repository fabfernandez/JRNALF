package com.mercadolibre.finalchallengedemo.service;


import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderStatusResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.partsorders.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.partsorders.PartOrderQueryParamsDTO;

public interface IOrderService {

    DealerOrderResponseDTO getOrders(PartOrderQueryParamsDTO params);

    public DealerOrderResponseDTO getOrdersByDealerAndStatus(String dealerNumber, String deliveryStatus,String country);
    public OrderStatusResponseDTO getOrdersFromDealersStatus(OrderStatusQueryParamsDTO orderStatusCMDTO);
}
