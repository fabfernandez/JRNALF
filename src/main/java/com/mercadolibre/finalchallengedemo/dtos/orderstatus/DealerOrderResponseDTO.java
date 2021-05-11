package com.mercadolibre.finalchallengedemo.dtos.orderstatus;

import com.mercadolibre.finalchallengedemo.dtos.orderstatus.OrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealerOrderResponseDTO {

    private Integer dealerNumber;
    private List<OrderDetailsDTO> orders;
}
