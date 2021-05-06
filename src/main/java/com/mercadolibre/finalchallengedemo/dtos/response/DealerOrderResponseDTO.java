package com.mercadolibre.finalchallengedemo.dtos.response;

import com.mercadolibre.finalchallengedemo.dtos.OrderResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DealerOrderResponseDTO {

    private Integer dealerNumber;
    private List<OrderResponseDTO> orders;
}
