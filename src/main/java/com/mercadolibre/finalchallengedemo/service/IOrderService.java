package com.mercadolibre.finalchallengedemo.service;


import com.mercadolibre.finalchallengedemo.dtos.partsorders.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.partsorders.PartOrderQueryParamsDTO;

public interface IOrderService {

    DealerOrderResponseDTO getOrders(PartOrderQueryParamsDTO params);

}
