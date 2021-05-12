package com.mercadolibre.finalchallengedemo.dtos;

import lombok.Data;

@Data
public class OrderUpdateRequestDTO {
    Integer orderNumber;
    Character statusCode;
}
