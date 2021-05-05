package com.mercadolibre.finalchallengedemo.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
public class PartDTO {

    private Integer partCode;
    private String description;
    private Integer netWeight;
    private Integer longDimension;
    private Integer widthDimension;
    private Integer tallDimension;

    private Integer normalPrice;

}
