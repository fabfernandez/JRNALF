package com.mercadolibre.finalchallengedemo.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrdersDetailsDTO {

    private Integer partCode;
    private String description;
    private Integer quantity;
    private String accountType;
    private String reason;

}
