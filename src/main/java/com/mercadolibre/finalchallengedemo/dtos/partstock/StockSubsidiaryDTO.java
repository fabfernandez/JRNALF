package com.mercadolibre.finalchallengedemo.dtos.partstock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockSubsidiaryDTO {

    private Integer stockId;
    private Integer part;
    private Integer quantity;
    private Integer subsidiary;

}
