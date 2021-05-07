package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DealerOrderResponseDTO {

    private Integer dealerNumber;
    private List<OrderDetailsDTO> orders;
}
