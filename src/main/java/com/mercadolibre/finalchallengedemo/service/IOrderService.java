package com.mercadolibre.finalchallengedemo.service;

import com.mercadolibre.finalchallengedemo.dtos.OrderResponseDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartDTO;
import com.mercadolibre.finalchallengedemo.dtos.PartFilterDTO;
import com.mercadolibre.finalchallengedemo.dtos.response.PartResponseDTO;

public interface IOrderService {

    OrderResponseDTO getAll();

    void deleteOrder(Integer id);

    void saveOrder(PartDTO part);

    OrderResponseDTO findOrder(Integer id);
}
