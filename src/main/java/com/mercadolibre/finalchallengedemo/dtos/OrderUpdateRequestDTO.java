package com.mercadolibre.finalchallengedemo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateRequestDTO {
    Integer orderNumber;
    Character statusCode;
}
