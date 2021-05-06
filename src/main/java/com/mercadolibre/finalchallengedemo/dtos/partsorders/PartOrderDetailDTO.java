package com.mercadolibre.finalchallengedemo.dtos.partsorders;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartOrderDetailDTO {

    private Integer partCode;
    private String description;
    private Integer quantity;
    private String accountType;
    private String reason;

}
