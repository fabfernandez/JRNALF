package com.mercadolibre.finalchallengedemo.dtos.partsorders;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DealerOrderResponseDTO {

    private Integer dealerNumber;
    private List<OrderDetailsDTO> ordersDetails;
}
