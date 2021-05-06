package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.OrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.partsorders.PartOrderQueryParamsDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.DealerOrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;

public interface IOrderService {

    DealerOrderResponseDTO getOrders(PartOrderQueryParamsDTO params);

}
