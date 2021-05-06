package com.mercadolibre.finalchallengedemo.dtos.partsorders;

import lombok.Data;

import java.util.List;
@Data
public class DealerOrderResponseDTO {

    private Integer dealerNumber;
    private List<OrderDetailsDTO> ordersDetails;
}
